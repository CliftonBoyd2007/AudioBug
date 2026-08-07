package com.CliftonBoyd2007.AudioBug.core;


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
    private boolean notifiedUserOfStartup;

    /**
     * Registers a new instance of {@link WatchCaret} with the caret model of the newly-created editor.
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
     * Notifies the user when AudioBug initially starts.
     *
     * @param feedbackService The service to produce auditory feedback.
     */
    private void audioBugStartupNotifier(FeedbackService feedbackService) {
        if (!this.notifiedUserOfStartup) {
            feedbackService.player.playCue_Start();
            feedbackService.announce("AudioBug started.", true);
            this.notifiedUserOfStartup = true;
        } else {
            return;
        }
    }
}
