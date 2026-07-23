package com.CliftonBoyd2007.AudioBug.accessibility;

import com.CliftonBoyd2007.AudioBug.core.services.FeedbackService;
import com.CliftonBoyd2007.AudioBug.core.services.HighlightStateService;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

import java.util.List;

/**
 * Decides which highlight type should be announced to the user.
 * AudioBug prioritizes errors over warnings to avoid overwhelming the user with information. This is particularly important if both errors and warnings coexist on the same line.
 * With this in mind, please do not modify that logic.
 *
 * @author Clifton Boyd
 */
@Service(Service.Level.PROJECT)
public final class HighlightAnnouncerService {
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


    public HighlightAnnouncerService(Project project) {

        this.project = project;
    }


    /**
     * Retrieves the updated list of highlights from the {@link HighlightStateService}.
     */
    public void updateService() {
        HighlightStateService service = this.project.getService(HighlightStateService.class);
        this.errors = service.getErrors();
        this.warnings = service.getWarnings();
        announceHighlightType();
    }

    /**
     * Indicates whether AudioBug should announce that there is an error on the current line of the caret.
     *
     * @return true if the list of errors is not empty.
     */
    public boolean shouldMakeErrorCallout() {
        return !this.errors.isEmpty();
    }

    /**
     * Indicates whether AudioBug should announce that there is a warning on the current line of the caret.
     *
     * @return true if the list of warnings is not empty and the list of errors is empty.
     */
    public boolean shouldMakeWarningCallout() {
        return !this.warnings.isEmpty() && this.errors.isEmpty();
    }

    /**
     * Announces the highlight type of greatest precedence.
     */
    private void announceHighlightType() {
        FeedbackService feedbackService = this.project.getService(FeedbackService.class);


        if (shouldMakeErrorCallout()) {
            feedbackService.player.playCue_Error();
            feedbackService.announce("Error.", true);
        } else if (shouldMakeWarningCallout()) {
            feedbackService.player.playCue_Warning();
            feedbackService.announce("Warning.", true);
        }
    }


}