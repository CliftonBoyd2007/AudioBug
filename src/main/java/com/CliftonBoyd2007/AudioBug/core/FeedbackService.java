package com.CliftonBoyd2007.AudioBug.core;


import com.intellij.openapi.components.Service;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;

@Service(Service.Level.PROJECT)
public final class FeedbackService {
    private final CuePlayer player = new CuePlayer();
    private final Project project;


    public FeedbackService(Project project) {
        this.project = project;
    }

    /**
     * Announces the highlight type of greatest precedence and plays its respective cue.
     * <p>
     * The precedence rule is as follows:
     * Error > Warning
     * This is to keep output as minimal as possible, particularly when errors and warnings coexist on the same line.
     * </p>
     * Please do not modify the logic that retains this model of precedence. We must keep output as minimal as possible to avoid overwhelming the user.
     */
    private void announceHighlightType() {
        HighlightStateService service = this.project.getService(HighlightStateService.class);

        if (!service.getErrors().isEmpty()) {
            this.player.playCue_Error();
            AccessibleAnnouncerUtil.announce(this.editorComponent.getAccessibleContext().getAccessibleParent(), "Error.", true);


        } else if (!service.getWarnings().isEmpty() && service.getErrors().isEmpty()) {
            this.player.playCue_Warning();
            AccessibleAnnouncerUtil.announce(this.editorComponent.getAccessibleContext().getAccessibleParent(), "Warning.", true);
        }
    }
}
