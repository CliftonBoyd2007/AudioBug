package com.cliftonboyd2007.audiobug.core;


import com.cliftonboyd2007.audiobug.core.services.caretutils.listeners.CaretWatcher;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import org.jetbrains.annotations.NotNull;

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


    /**
     * Registers a new instance of {@link CaretWatcher} with the caret model of the newly-created editor so the caret can be tracked.
     *
     * @param event The event containing information about the editor.
     */
    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        CaretModel caretModel = event.getEditor().getCaretModel();
        caretModel.addCaretListener(this.caretWatcher);

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


}
