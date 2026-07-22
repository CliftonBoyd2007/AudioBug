package com.CliftonBoyd2007.AudioBug.accessibility;

import com.CliftonBoyd2007.AudioBug.core.HighlightStateService;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;

import javax.swing.JComponent;
import java.util.List;

/**
 * Responsible for triggering screen reader announcements and audio cue playback for  highlights on the current line.
 *
 * @author Clifton Boyd
 */
public class LineHighlightAudioizer {
    /**
     * Backing store for error highlights.
     */
    private List<HighlightInfo> errors;
    /**
     * Backing store for warning highlights.
     */
    private List<HighlightInfo> warnings;
    /**
     * The current project.
     */
    private final Project project;
    /**
     * Object responsible for audio playback management.
     */
//    private final CuePlayer player;
    /**
     * Global flag that determines whether audio cues will be played.
     */
//    private final boolean isAudioCuesEnabled;
    /**
     * The editor UI component from which screen reader announcements will originate.
     */
    private JComponent editorComponent;

    public LineHighlightAudioizer(Project project) {
//        this.player = new CuePlayer();
//        this.isAudioCuesEnabled = this.player.getAllFilesAvailable();
        this.project = project;
    }

    /**
     * Updates the current editor {@link JComponent} in the event that it has changed.
     *
     * @param editorComponent The new editor component.
     */
    public void updateEditorComponent(JComponent editorComponent) {
        if (editorComponent == null) {
            return;
        }

        this.editorComponent = editorComponent;

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




}