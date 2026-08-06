package com.CliftonBoyd2007.AudioBug.core.actions;

import com.CliftonBoyd2007.AudioBug.accessibility.HighlightAnnouncerService;
import com.intellij.openapi.actionSystem.AnAction;

import com.intellij.openapi.actionSystem.AnActionEvent;


/**
 * An action for reading the highlight descriptions (e.g., "';' expected"), aloud with the active screen reader on-demand.
 * Copyright Clifton Boyd and AudioBug Contributors.
 */
public class ReadHighlightDescriptionAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        assert event.getProject() != null;
        HighlightAnnouncerService announcerService = event.getProject().getService(HighlightAnnouncerService.class);
        announcerService.announceHighlightDescription();
    }


}
