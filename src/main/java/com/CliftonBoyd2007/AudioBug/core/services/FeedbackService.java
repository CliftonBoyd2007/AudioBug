package com.CliftonBoyd2007.AudioBug.core.services;


import com.CliftonBoyd2007.AudioBug.core.AudioBug_Init;
import com.intellij.openapi.components.Service;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;
import javax.accessibility.Accessible;
import javax.swing.*;


/**
 * Service responsible for user-facing feedback.
 * It controls both speech and audio cue playback.
 *
 * @author Clifton Boyd
 */
@Service(Service.Level.PROJECT)
public final class FeedbackService {
    public final CuePlayer player = new CuePlayer();
    private final Project project;
    private Accessible accessibleEditorUIComponent;

    public FeedbackService(Project project) {
        this.project = project;
    }

    /**
     * Make an announcement with the active screen reader.
     *
     * @param message                      the message to announce.
     * @param interruptCurrentSpeechOutput determine whether to interrupt current screen reader speech for this announcement.
     */
    public void announce(String message, boolean interruptCurrentSpeechOutput) {
        AccessibleAnnouncerUtil.announce(this.accessibleEditorUIComponent, message, interruptCurrentSpeechOutput);
    }

    /**
     * Update the editor UI component from which screen reader announcements will originate.
     * This must only be called in {@link AudioBug_Init#editorCreated(EditorFactoryEvent)}. Please do not call this yourself.
     *
     * @param editorComponent the component to retrieve accessible context from.
     */
    public void updateAccessibleEditorUIComponent(JComponent editorComponent) {
        this.accessibleEditorUIComponent = editorComponent.getAccessibleContext().getAccessibleParent();


    }
}