package com.CliftonBoyd2007.AudioBug.accessibility;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;
import com.jetbrains.AccessibleAnnouncer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;

import java.io.IOException;
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
//    private final CuePlayer player;
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
//        this.player = new CuePlayer();
//        this.isAudioCuesEnabled = this.player.getAllFilesAvailable();

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
        // Avoid unnecessarily updating the editor component.
        if (this.editorComponent.equals(editorComponent)) {
            return;
        }
        this.editorComponent = editorComponent;

    }

    /**
     * Updates the list of errors and warnings.
     *
     * @param newErrors   The new list of error highlights.
     * @param newWarnings The new list of warning highlights.
     */
    public void updateHighlights(ArrayList<HighlightInfo> newErrors, ArrayList<HighlightInfo> newWarnings) {

        this.errors = newErrors;
        this.warnings = newWarnings;
    }

    public void announceHighlightType_audiotoryOnly() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        if (errors.size() > 0) {
            AccessibleAnnouncerUtil.announce(this.editorComponent.getAccessibleContext().getAccessibleParent(), "Error.", true);


        } else if (warnings.size() > 0 && errors.size() == 0) {
            AccessibleAnnouncerUtil.announce(this.editorComponent.getAccessibleContext().getAccessibleParent(), "Error.", true);
        } else {
            return;
        }
    }


}