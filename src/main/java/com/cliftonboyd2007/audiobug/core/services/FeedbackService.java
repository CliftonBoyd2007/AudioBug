package com.cliftonboyd2007.audiobug.core.services;


import com.cliftonboyd2007.audiobug.core.AudioBug_Init;
import com.intellij.openapi.components.Service;
import com.cliftonboyd2007.audiobug.audioutils.CuePlayer;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;

import javax.accessibility.Accessible;
import javax.swing.*;

import com.cliftonboyd2007.audiobug.audioutils.Cue;

/**
 * Service responsible for user-facing feedback.
 * It controls both speech and audio cue playback.
 * Copyright Clifton Boyd and AudioBug Contributors.
 *
 * @author Clifton Boyd
 */
@Service(Service.Level.PROJECT)
public final class FeedbackService {
    private final CuePlayer player = new CuePlayer();

    /**
     * The UI component from which screen reader announcements will originate.
     */
    private Accessible accessibleEditorUIComponent;

    /**
     * Constructor.
     * <p>
     * Please do not call this yourself.
     * This exists for the IntelliJ Platform so that it can construct this service when it is required.
     * To obtain this service elsewhere, use {@link Project#getService(Class)}.
     * </p>
     */
    public FeedbackService() {

    }

    /**
     * Make an announcement with the active screen reader.
     *
     * @param message                      the string to announce.
     * @param interruptCurrentSpeechOutput determine whether to interrupt current screen reader speech for this announcement.
     */
    public void announce(String message, boolean interruptCurrentSpeechOutput) {
        AccessibleAnnouncerUtil.announce(this.accessibleEditorUIComponent, message, interruptCurrentSpeechOutput);
    }

    /**
     * Update the editor UI component from which screen reader announcements will originate.
     * This must only be called in {@link AudioBug_Init#editorCreated(EditorFactoryEvent)}. Please do not call this yourself.
     * We do this because we must ensure that the {@link Accessible} we are using to make screen reader announcements with is valid.
     *
     * @param editorComponent the component to retrieve accessible context from.
     */
    public void updateAccessibleEditorUIComponent(JComponent editorComponent) {
        this.accessibleEditorUIComponent = editorComponent.getAccessibleContext().getAccessibleParent();


    }

    /**
     * Plays the given audio cue.
     *
     * @param cue the cue to be played
     * @see Cue
     */
    public void playCue(Cue cue) {
        this.player.playCue(cue);
    }


}