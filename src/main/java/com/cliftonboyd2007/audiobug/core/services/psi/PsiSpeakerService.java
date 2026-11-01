package com.cliftonboyd2007.audiobug.core.services.psi;

import com.intellij.openapi.components.Service;
import com.cliftonboyd2007.audiobug.core.services.accessibility.FeedbackService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * DO NOT USE THIS SERVICE!
 * <p>
 * This was an experiment, and is almost certainly broken. Do not call anything in here in a public release.
 * </p>
 * <p>
 * If you do decide to use this in your own local builds, may God, luck, and the force be with you.
 * </p>
 *
 * @author Clifton Boyd
 */

@Service(Service.Level.PROJECT)
public final class PsiSpeakerService {
    /**
     * The project from which we obtain other services.
     */
    private final Project project;

    /**
     * Constructor.
     * <p>
     * Please do not call this yourself.
     * This exists for the IntelliJ Platform so that it can construct this service when it is required.
     * To obtain this service elsewhere, use {@link Project#getService(Class)}.
     * </p>
     */
    public PsiSpeakerService(@NotNull final Project project) {
        this.project = project;
    }

    /**
     * Announces the string returned by {@link PsiElement#toString()}.
     * <p>
     * If no element is found (i.e., {@link PsiService#getPsiElementAtCaret()} returns {@code null}, announce that no PSI element was found.
     * This is for experimental purposes only. This is not to be used in a stable release.
     * </p>
     */
    public void speakPsiElement() {
        FeedbackService feedbackService = this.project.getService(FeedbackService.class);
        PsiService psiService = this.project.getService(PsiService.class);
        PsiElement elementToSpeak_Leaf = psiService.getPsiElementAtCaret();


        if (elementToSpeak_Leaf != null) {
            PsiElement elementToSpeak = elementToSpeak_Leaf.getParent();
            feedbackService.announce(elementToSpeak.toString(), true);
            return;
        }
        feedbackService.announce("No PSI element", true);
    }
}
