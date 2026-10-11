package com.cliftonboyd2007.audiobug.core.services.psi;

import com.intellij.openapi.components.Service;
import com.cliftonboyd2007.audiobug.core.services.accessibility.FeedbackService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service(Service.Level.PROJECT)
public final class PsiSpeakerService {
    private final Project project;

    public PsiSpeakerService(@NotNull final Project project) {
        this.project = project;
    }

    public void speakPsiElement() {
        PsiService psiService = this.project.getService(PsiService.class);
        PsiElement elementToSpeak_Leaf = psiService.getPsiElementAtCaret();
        PsiElement elementToSpeak_Parent;
        elementToSpeak_Parent = elementToSpeak_Leaf.getParent().getParent();


        FeedbackService feedbackService = this.project.getService(FeedbackService.class);
        if (elementToSpeak_Parent    != null ) {
            feedbackService.announce(elementToSpeak_Parent.toString(), true);
            return;
        }
        feedbackService.announce("No PSI element.", true);
    }
}
