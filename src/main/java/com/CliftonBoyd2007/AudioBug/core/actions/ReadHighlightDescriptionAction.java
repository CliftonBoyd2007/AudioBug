package com.CliftonBoyd2007.AudioBug.core.actions;

import com.CliftonBoyd2007.AudioBug.accessibility.HighlightAnnouncerService;
import com.intellij.openapi.actionSystem.AnAction;
import com.CliftonBoyd2007.AudioBug.core.services.HighlightStateService;
import com.CliftonBoyd2007.AudioBug.core.services.FeedbackService;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;

public class ReadHighlightDescriptionAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
//        assert event.getProject() != null;
        HighlightAnnouncerService announcerService = event.getProject().getService(HighlightAnnouncerService.class);
        announcerService.announceHighlightDescription();
    }


}
