package com.cliftonboyd2007.audiobug.core.services.psi;

import com.cliftonboyd2007.audiobug.core.services.caretutils.CaretStateService;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * DO NOT USE THIS SERVICE!
 * <p>
 * This was an experiment, and is almost certainly broken. Do not call anything in here in a public release.
 * I am keeping this class around for future implementation ideas and nothing more.
 * </p>
 * <p>
 * If you do decide to use this in your own local builds, may God, luck, and the force be with you.
 * </p>
 *
 * @author Clifton Boyd
 */

@Service(Service.Level.PROJECT)
public final class PsiService {
    /**
     * The project from which we obtain other services.
     */
    private final Project project;
    /**
     * The document from which we can obtain the correct {@link PsiFile}
     */
    private Document document;

    /**
     * The PsiFile whose PSI tree we want to traverse.
     */
    private PsiFile psiFile;

    /**
     * Constructor.
     * <p>
     * Please do not call this yourself.
     * This exists for the IntelliJ Platform so that it can construct this service when it is required.
     * To obtain this service elsewhere, use {@link Project#getService(Class)}.
     * </p>
     */
    public PsiService(@NotNull final Project project) {
        this.project = project;
    }

    /**
     * Updates the document instance.
     * <p>
     * We do this to ensure that we obtain the correct {@link PsiFile}.
     * </p>
     *
     * @param newDocument the new document whose PSI File we need
     */
    private void updateDocument(@NotNull final Document newDocument) {
        this.document = newDocument;
    }

    /**
     * Ensures instance fields are up to date.
     * <p>
     * This is called on every caret movement because playing pointer musical chairs is not expensive. Nothing is being constructed.
     * </p>
     *
     * @param event the event resulting from caret movement
     */
    public void update(@NotNull final CaretEvent event) {
        updateDocument(event.getEditor().getDocument());
        updatePsiFile();

    }

    /**
     * Updates the PsiFile instance.
     * <p>
     * BE CAREFUL! {@link PsiDocumentManager#getPsiFile(Document)} CAN RETURN NULL.
     * Please check {@link #psiFile} to ensure it is not null before using it.
     * </p>
     */
    private void updatePsiFile() {
        PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(this.project);
        this.psiFile = psiDocumentManager.getPsiFile(this.document);
    }

    /**
     * Gets the PSI element under the caret.
     *
     * @return either the PSI element under the caret, or {@code null} if no element is found.
     */
    public @Nullable PsiElement getPsiElementAtCaret() {

        CaretStateService caretStateService = this.project.getService(CaretStateService.class);
        final int caretOffset = caretStateService.getCaretOffset();
        if (this.psiFile != null) {
            return this.psiFile.findElementAt(caretOffset);
        }
        return null;
    }


}
