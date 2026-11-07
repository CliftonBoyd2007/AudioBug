package com.cliftonboyd2007.audiobug.core;


import com.cliftonboyd2007.audiobug.audioutils.Cue;
import com.cliftonboyd2007.audiobug.core.listeners.CaretWatcher;
import com.cliftonboyd2007.audiobug.core.services.accessibility.FeedbackService;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.ApplicationInfo;

/**
 * Initializes AudioBug for newly-created editor instances.
 * <p>
 * When an editor is created, this will attach an instance of {@link CaretWatcher} to the caret model of the editor that was just created so that AudioBug can monitor the caret.
 * </p>
 *
 * @author Clifton Boyd
 */
public final class AudioBug_Init implements EditorFactoryListener {
    /**
     * Indicates whether AudioBug has played the startup cue and made the startup announcement.
     */
    private boolean notifiedUserOfStartup;
    /**
     * Tracks the caret within an active editor.
     * <p>
     * An "active" editor, in the case of AudioBug, refers to the editor in which the user is working in. It does not refer to an editor that is displayed on screen.
     * We re-use this CaretWatcher for every editor because a screen-reader user cannot generally operate in more than one editor at a time, even if more than one is open on the screen. It also avoids UI jitter when switching between editors.
     * </p>
     */
    private final CaretWatcher caretWatcher = new CaretWatcher();

    private boolean ideVersionHasBeenChecked = false;

    /**
     * Registers a new instance of {@link CaretWatcher} with the caret model of the newly-created editor so the caret can be tracked.
     *
     * @param event The event containing information about the editor.
     */
    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        CaretModel caretModel = event.getEditor().getCaretModel();
        caretModel.addCaretListener(this.caretWatcher);
        Project project = event.getEditor().getProject();
        assert project != null;
        FeedbackService feedbackService = project.getService(FeedbackService.class);
        feedbackService.updateAccessibleEditorUIComponent(event.getEditor().getComponent());
        audioBugStartupNotifier(feedbackService);
        if (!ideVersionHasBeenChecked) {
            disableCuePlayerOnIDETooNew(feedbackService);
            this.ideVersionHasBeenChecked = true;
        }
    }

    /**
     * Removes the caret listener when an editor is released.
     * <p>
     * An editor is released when it is closed, not when the user switches away from it.
     * </p>
     *
     * @param event the event resulting from an  editor being released
     */
    @Override
    public void editorReleased(@NotNull EditorFactoryEvent event) {
        Editor releasedEditor = event.getEditor();
        releasedEditor.getCaretModel().removeCaretListener(this.caretWatcher);

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

    /**
     * Disables AudioBug's own cues if running a JetBrains IDE version >= 2026.3.
     * <p>
     * In IDE versions >= 2026.3, JetBrains has implemented their own cues. AudioBug disables its own cues to avoid duplicated auditory feedback. Spoken announcements by AudioBug are still enabled.
     * </p>
     *
     * @param feedbackService The service producing spoken and auditory feedback.
     */
    private void disableCuePlayerOnIDETooNew(@NotNull final FeedbackService feedbackService) {
        ApplicationInfo applicationInfo = ApplicationInfo.getInstance();
        String ideMajorVersionString = applicationInfo.getMajorVersion();
        String ideMinorVersionString = applicationInfo.getMinorVersion();
        // We convert the IDE major version to an Integer because we need to be able to use < and > operators.
        try {
            int ideMajorVersion = Integer.parseInt(ideMajorVersionString);
            int ideMinorVersion = Integer.parseInt(ideMinorVersionString);
            feedbackService.setIsAudioBugCuesDisabled(
                    (ideMajorVersion > 2026)
                            || (ideMajorVersion == 2026 && ideMinorVersion >= 3));


        } catch (NumberFormatException e) {
            // Disable them anyway. If we can't determine the version number, it's probably best to turn them off in case we're running an EAP. The user can re-enable them manually if they so choose.
            feedbackService.setIsAudioBugCuesDisabled(true);
        }
    }

}