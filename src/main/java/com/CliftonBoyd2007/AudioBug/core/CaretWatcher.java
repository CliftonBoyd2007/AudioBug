package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;
import org.jetbrains.annotations.NotNull;


public class CaretWatcher implements CaretListener {
    private LineHighlightLocator locator;

    public CaretWatcher() {

    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        LogicalPosition oldPosition = event.getOldPosition();
        LogicalPosition newPosition = event.getNewPosition();
        boolean isCaretMovedToNewLine = hasCaretMovedToNewLine(oldPosition, newPosition);
        if (isCaretMovedToNewLine) {
            highlightLocatorUpdateHelper(event);
        } else {
            return;
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
    private void highlightLocatorUpdateHelper(@NotNull CaretEvent event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (this.locator == null) {
            this.locator = new LineHighlightLocator(event);
        } else {
            this.locator.update(event);
        }
    }


}
