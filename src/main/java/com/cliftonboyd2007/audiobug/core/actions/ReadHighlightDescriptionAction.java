package com.cliftonboyd2007.audiobug.core.actions;

import com.cliftonboyd2007.audiobug.accessibility.HighlightAnnouncerService;
import com.intellij.openapi.actionSystem.AnAction;

import com.intellij.openapi.actionSystem.AnActionEvent;


/**
 * Reads highlight descriptions aloud with the active screen reader on demand.
 * <p>
 * For example, an error such as "';' expected" can be spoken to the user when requested.
 * If multiple highlights exist, the user can repeatedly invoke this action to cycle through each one. Once the last highlight is reached, invoking this action again will read the first highlight description.
 * </p>
 * <p>
 * This action can be invoked by pressing alt+R on Windows or option+R on macOS.
 * </p>
 *
 * @author Clifton Boyd
 */
public class ReadHighlightDescriptionAction extends AnAction {

    /**
     * Announces the description of highlights on the current line of the caret.
     *
     * @param event the event resulting from invoking this action
     * @see HighlightAnnouncerService#announceHighlightDescription()
     */
    @Override
    public void actionPerformed(AnActionEvent event) {
        assert event.getProject() != null;
        HighlightAnnouncerService announcerService = event.getProject().getService(HighlightAnnouncerService.class);
        announcerService.announceHighlightDescription();
    }


}
