package com.cliftonboyd2007.audiobug.core.actions;

import com.cliftonboyd2007.audiobug.core.services.accessibility.FeedbackService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ToggleAudioBugCuesAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        Project project = event.getProject();
        FeedbackService feedbackService = project.getService(FeedbackService.class);

        /*
        If this is true, then AudioBug's cues were originally disabled. If it was false, they were originally enabled, and will be disabled on the next call to feedbackService.toggleIsAudioBugCuesDisabled().
        */
        boolean oldAudioBugSoundCuesAreDisabled = feedbackService.getIsAudioBugCuesDisabled();
        feedbackService.toggleIsAudioBugCuesDisabled();
        /*
        If this is true, then AudioBug's sound cues were originally disabled. If this is false, then AudioBug's sound cues were originally disabled, and were enabled on the last call to feedbackService.toggleIsAudioBugCuesDisabled().
         */
        boolean newAudioBugSoundCuesAreDisabled = feedbackService.getIsAudioBugCuesDisabled();
        if (oldAudioBugSoundCuesAreDisabled && !newAudioBugSoundCuesAreDisabled) {
            feedbackService.announce("AudioBug sound cues enabled.", true);
        } else if (!oldAudioBugSoundCuesAreDisabled && newAudioBugSoundCuesAreDisabled) {
            feedbackService.announce("AudioBug sound cues disabled.", true);
        }
    }
}
