package com.CliftonBoyd2007.AudioBug.core.actions;

import com.CliftonBoyd2007.AudioBug.accessibility.HighlightAnnouncerService;
import com.intellij.openapi.actionSystem.AnAction;

import com.intellij.openapi.actionSystem.AnActionEvent;


/**
 * An action for reading the highlight descriptions (e.g., "';' expected"), aloud with the active screen reader on-demand.
 * Copyright Clifton Boyd and AudioBug Contributors.
 *
 * @author Clifton Boyd
 */
public class ReadHighlightDescriptionAction extends AnAction {

    /**
     * Announces the description of errors/warnings on the current line of the caret.
     * This action is invoked when the user presses alt+R (on Windows); option+R (on macOS).
     *
     * @param event the event resulting from the user pressing the above keyboard shortcut
     * @see HighlightAnnouncerService#announceHighlightDescription()
     */
    @Override
    public void actionPerformed(AnActionEvent event) {
        assert event.getProject() != null;
        HighlightAnnouncerService announcerService = event.getProject().getService(HighlightAnnouncerService.class);
        announcerService.announceHighlightDescription();
    }


}
