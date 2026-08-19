package com.cliftonboyd2007.audiobug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import org.jetbrains.annotations.NotNull;


/**
 * Listens to caret movement and determines whether AudioBug should respond when the caret changes position.
 *
 * <p>
 * It distinguishes between vertical and lateral movement to avoid overwhelming the user with feedback and to determine if AudioBug should begin querying for error/warning highlights.
 * </p>
 *
 * @author Clifton Boyd
 */
public class WatchCaret implements CaretListener {
    /**
     * Queries for and collects error/warning highlights.
     */
    private LineHighlightLocator locator;

    /**
     * Constructor.
     * <p>
     * Please DO NOT call this yourself. This is only here for {@link AudioBug_Init}.
     * </p>
     */
    WatchCaret() {

        // locator is managed by highlightLocatorUpdateHelper().
        // We do not initialize locator here because we cannot correctly construct it without a CaretEvent.


    }

    /**
     * Responds to caret events within the editor.
     * When the caret moves, it determines if it has moved to a new line or not and responds accordingly.
     *
     * @param event the event containing information about the caret.
     */
    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        LogicalPosition oldPosition = event.getOldPosition();
        LogicalPosition newPosition = event.getNewPosition();
        boolean caretHasMovedToNewLine = hasCaretMovedToNewLine(oldPosition, newPosition);
        if (caretHasMovedToNewLine) {
            highlightLocatorUpdateHelper(event);
        }
    }

    /**
     * Indicates whether the caret has moved to a new line.
     *
     * @param oldPosition The previous position of the caret.
     * @param newPosition The current position of the caret.
     * @return true if the line of the caret has changed.
     */
    private boolean hasCaretMovedToNewLine(LogicalPosition oldPosition, LogicalPosition newPosition) {
        return oldPosition.line != newPosition.line;
    }

    /**
     * Helper method for maintaining the LineHighlightLocator instance.
     * We do this here to avoid throwing {@link NullPointerException} when updating it.
     *
     * @param event the event containing information about the caret.
     */
    private void highlightLocatorUpdateHelper(@NotNull CaretEvent event) {
        if (this.locator == null) {
            this.locator = new LineHighlightLocator(event);
        } else {
            this.locator.update(event);
        }
    }
}