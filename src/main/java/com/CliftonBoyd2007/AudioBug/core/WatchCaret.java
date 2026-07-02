package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import org.jetbrains.annotations.NotNull;

public class WatchCaret implements CaretListener {
    private LineHighlightLocator locator;

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        LogicalPosition oldPosition = event.getOldPosition();
        LogicalPosition newPosition = event.getNewPosition();
        boolean caretMovedToNewLine = hasCaretMovedToNewLine(oldPosition, newPosition);
        if (caretMovedToNewLine) {
            highlightLocatorUpdateHelper(event);
        }

    }

    /**
     * Indicates whether the caret has moved to a new line.
     *
     * @param oldPosition The previous position of the caret.
     * @param newPosition The current position of the caret.
     * @return true if the new position is on a different line from the old position.
     */
    private boolean hasCaretMovedToNewLine(LogicalPosition oldPosition, LogicalPosition newPosition) {
        return oldPosition.line != newPosition.line;
    }

    /**
     * Helper method for updating the {@link LineHighlightLocator} object safely.
     *
     * @param event Event containing information about the caret.
     */
    private void highlightLocatorUpdateHelper(@NotNull CaretEvent event) {
        if (this.locator == null) {
            this.locator = new LineHighlightLocator(event);
        } else {
            this.locator.update(event);
        }
    }
}