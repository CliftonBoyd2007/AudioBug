package com.CliftonBoyd2007.AudioBug.core;


import com.CliftonBoyd2007.AudioBug.audioutils.Cue;
import com.CliftonBoyd2007.AudioBug.core.services.FeedbackService;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Initializes AudioBug for newly-created editor instances.
 * <p>
 * When an editor is created, this will attach an instance of {@link WatchCaret} to the caret model of the editor that was just created.
 * </p>
 * Copyright Clifton Boyd and AudioBug Contributors.
 *
 * @author Clifton Boyd
 */
public final class AudioBug_Init implements EditorFactoryListener {
    /**
     * Indicates whether AudioBug has played the startup cue and made the startup announcement.
     */
    private boolean notifiedUserOfStartup;

    /**
     * Registers a new instance of {@link WatchCaret} with the caret model of the newly-created editor so the caret can be tracked.
     *
     * @param event The event containing information about the editor.
     */
    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        CaretModel caretModel = event.getEditor().getCaretModel();
        caretModel.addCaretListener(new WatchCaret());
        Project project = event.getEditor().getProject();
        assert project != null;
        FeedbackService feedbackService = project.getService(FeedbackService.class);
        feedbackService.updateAccessibleEditorUIComponent(event.getEditor().getComponent());
        audioBugStartupNotifier(feedbackService);
    }

    /**
     * Plays the startup sound cue and makes a readiness announcement upon startup.
     *
     * @param feedbackService The service to produce spoken and auditory feedback.
     */
    private void audioBugStartupNotifier(FeedbackService feedbackService) {
        if (!this.notifiedUserOfStartup) {
            feedbackService.playCue(Cue.STARTUP);
            feedbackService.announce("AudioBug is ready.", true);
            this.notifiedUserOfStartup = true;
        }
    }
}
