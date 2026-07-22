package com.CliftonBoyd2007.AudioBug.accessibility;

import com.CliftonBoyd2007.AudioBug.core.services.FeedbackService;
import com.CliftonBoyd2007.AudioBug.core.services.HighlightStateService;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.openapi.project.Project;

import java.util.List;

/**
 * Responsible for triggering screen reader announcements and audio cue playback for  highlights on the current line.
 *
 * @author Clifton Boyd
 */
public class LineHighlightAudioizer {
    /**
     * Temporary storage for error highlights from {@link HighlightStateService}.
     */
    private List<HighlightInfo> errors;
    /**
     * Temporary storage for warning highlights from {@link HighlightStateService}.
     */
    private List<HighlightInfo> warnings;
    /**
     * The current project from which we obtain required services.
     */
    private final Project project;


    public LineHighlightAudioizer(Project project) {

        this.project = project;
    }


    /**
     * Retrieves the updated list of highlights from the {@link HighlightStateService}.
     */
    public void updateAudioizer() {
        HighlightStateService service = this.project.getService(HighlightStateService.class);
        this.errors = service.getErrors();
        this.warnings = service.getWarnings();
        announceHighlightType();
    }

    private void announceHighlightType() {
        FeedbackService feedbackService = this.project.getService(FeedbackService.class);


        if (!this.errors.isEmpty()) {
            feedbackService.player.playCue_Error();
            feedbackService.announce("Error.", true);
        } else if (!this.warnings.isEmpty() && this.errors.isEmpty()) {
            feedbackService.player.playCue_Warning();
            feedbackService.announce("Warning.", true);
        }
    }


}