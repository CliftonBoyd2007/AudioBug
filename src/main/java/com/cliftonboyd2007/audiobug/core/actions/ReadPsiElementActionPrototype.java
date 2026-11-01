package com.cliftonboyd2007.audiobug.core.actions;

import com.cliftonboyd2007.audiobug.core.services.psi.PsiSpeakerService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * DO NOT REGISTER OR USE THIS ACTION.
 * <p>
 * This was an experiment, and I'm foregoing it. I'm keeping it around for possible future implementation ideas for other features and nothing more. Again, do not use this under any circumstance in a public release.
 * And if you do decide to use this and the other AudioBug PSI-related abstractions (which I have also advised against using), in your own local builds, may God, luck, and patience be with you.
 * </p>
 *
 * @author Clifton Boyd
 */
public class ReadPsiElementActionPrototype extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        assert project != null;
        PsiSpeakerService psiSpeakerService = project.getService(PsiSpeakerService.class);
        psiSpeakerService.speakPsiElement();
    }
}
