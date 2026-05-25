package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import org.jetbrains.annotations.NotNull;

public class WatchCaret implements CaretListener {
    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        LogicalPosition oldPosition = event.getOldPosition();
        LogicalPosition newPosition = event.getNewPosition();
        boolean caretMovedToNewLine = hasCaretMovedToNewLine(oldPosition, newPosition);
        if (caretMovedToNewLine) {
            // React accordingly
        } else {
            return; // Do not overwhelm the user by reacting to caret movement on the same line.
        }

    }

    /**
     * Indicates whether the caret has moved to a new line.
     *
     * @param oldPosition The previous position of the caret.
     * @param newPosition The current position of the caret.
     * @return Whether the newPosition of the caret is on the same line as the old position.
     */
    private boolean hasCaretMovedToNewLine(LogicalPosition oldPosition, LogicalPosition newPosition) {
        return oldPosition.line != newPosition.line;
    }
}