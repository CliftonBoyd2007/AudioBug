package com.CliftonBoyd2007.AudioBug.core;


import com.intellij.openapi.components.Service;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;

import javax.accessibility.Accessible;

@Service(Service.Level.PROJECT)
public final class FeedbackService {
    private final CuePlayer player = new CuePlayer();
    private final Project project;
    private Accessible accessibleEditorUIComponent;

    public FeedbackService(Project project) {
        this.project = project;
    }

        private void announce(String message, boolean interruptCurrentSpeechOutput) {
        AccessibleAnnouncerUtil.announce(this.accessibleEditorUIComponent, message, interruptCurrentSpeechOutput);
    }
}
