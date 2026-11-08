package com.cliftonboyd2007.audiobug.core.actions;

import com.cliftonboyd2007.audiobug.core.services.accessibility.FeedbackService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Toggles AudioBug's auditory cues on or off.
 * <p>
 * The action will give spoken confirmation as to whether the cues were enabled or disabled.
 * User-facing output example (Disabling audio cues): "AudioBug sound cues disabled."
 * </p>
 * <p>
 * This action can be invoked by the user by pressing Ctrl + Alt + L on Windows, and Ctrl + Option + L on macOS.
 * Note that Ctrl-Option is the VoiceOver modifier, so the user must press Ctrl + Option + Tab before pressing Ctrl + Option + L to make VoiceOver pass the shortcut through to the IDE.
 * </p>
 *
 * @author Clifton Boyd
 */
public class ToggleAudioBugCuesAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {
        Project project = event.getProject();
        assert project != null;
        FeedbackService feedbackService = project.getService(FeedbackService.class);

        /*
        If this is true, then AudioBug's cues were originally disabled. If it was false, they were originally enabled, and will be disabled on the next call to feedbackService.toggleIsAudioBugCuesDisabled().
        */
        boolean oldAudioBugSoundCuesAreDisabled = feedbackService.getIsAudioBugCuesDisabled();
        feedbackService.toggleAudioBugCues();
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
