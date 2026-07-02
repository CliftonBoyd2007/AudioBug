package com.CliftonBoyd2007.AudioBug.accessibility;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;

import javax.swing.JComponent;

import java.util.ArrayList;

public class LineHighlightAudioizer {
    /**
     * Backing store for error highlights.
     */
    private ArrayList<HighlightInfo> errors;
    /**
     * Backing store for warning highlights.
     */
    private ArrayList<HighlightInfo> warnings;
    /**
     * Object responsible for audio playback management.
     */
    private CuePlayer player;
    /**
     * Global flag that determines whether audio cues will be played.
     */
    private boolean isAudioCuesEnabled;
    /**
     * The editor UI component from which screen reader announcements will originate.
     */
    private JComponent editorComponent;

    public LineHighlightAudioizer(ArrayList<HighlightInfo> errors, ArrayList<HighlightInfo> warnings) {
        this.errors = errors;
        this.warnings = warnings;
        this.player = new CuePlayer();
        this.isAudioCuesEnabled = this.player.getAllFilesAvailable();

    }

    /**
     * Updates the current editor {@link JComponent}.
     *
     * @param editorComponent The new editor component.
     */
    public void updateEditorComponent(JComponent editorComponent) {
        if (editorComponent == null) {
            return;
        }
        this.editorComponent = editorComponent;

    }

}
