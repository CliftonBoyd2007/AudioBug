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

@Service(Service.Level.PROJECT)
public final class PsiService {

    private final Project project;
    private Document document;
    private PsiFile psiFile;

    public PsiService(@NotNull Project project) {
        this.project = project;
    }


    private void updateDocument(@NotNull final Document newDocument) {
        this.document = newDocument;
    }

    public void update(@NotNull CaretEvent event) {
        updateDocument(event.getEditor().getDocument());
        updatePsiFile();

    }

    private void updatePsiFile() {
        PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(this.project);
        this.psiFile = psiDocumentManager.getPsiFile(this.document);
    }

    public @Nullable PsiElement getPsiElementAtCaret() {

        CaretStateService caretStateService = this.project.getService(CaretStateService.class);
        int caretOffset = caretStateService.getCaretOffset();
        if (this.psiFile != null) {
            return this.psiFile.findElementAt(caretOffset);
        }
        return null;
    }


}
