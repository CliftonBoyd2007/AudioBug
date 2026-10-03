package com.cliftonboyd2007.audiobug.core;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.Caret;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.PROJECT)
public final class AudioBugEditorStateModelService {
    /**
     * The primary caret of the editor to be tracked.
     */
    private Caret primaryCaret;

    public AudioBugEditorStateModelService() {
    }

    /**
     * Updates the primary caret to be tracked.
     *
     * @param newCaret the new primary caret
     */
    public void updatePrimaryCaret(@NotNull final Caret newCaret) {
        this.primaryCaret = newCaret;
    }

    /**
     * @return the current line of the primary caret
     */
    public int getCurrentLineOfPrimaryCaret() {
        return this.primaryCaret.getLogicalPosition().line;
    }
}
