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
        PsiElement elementToSpeak = psiService.getPsiElementAtCaret();
        FeedbackService feedbackService = this.project.getService(FeedbackService.class);
        if (elementToSpeak != null) {
            feedbackService.announce(elementToSpeak.toString(), true);
            return;
        }
        feedbackService.announce("No valid PSI element.", true);
    }
}
