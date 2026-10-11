package com.cliftonboyd2007.audiobug.core.actions;

import com.cliftonboyd2007.audiobug.core.services.psi.PsiSpeakerService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ReadPsiElementActionPrototype extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        assert project != null;
        PsiSpeakerService psiSpeakerService = project.getService(PsiSpeakerService.class);
        psiSpeakerService.speakPsiElement();
    }
}
