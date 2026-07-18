package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;


import org.jetbrains.annotations.NotNull;


import java.io.File;

/**
 * Listens to caret movement and determines whether AudioBug should respond when the caret changes position.
 *
 * <p>
 * It distinguishes between vertical and lateral movement to avoid overwhelming the user with feedback.
 *
 * @author Clifton Boyd
 */
public class WatchCaret implements CaretListener {
    /**
     * Object responsible for querying for highlights on in which the caret is located.
     */
    private LineHighlightLocator highlightLocator;

    /**
     * Constructs an instance of the WatchCaret class.
     * <p>
     * Please DO NOT call this yourself. This is only here for {@link AudioBug_Init}.
     */
    WatchCaret() {
        // Unless you are testing something, leave this method alone.
        // This only exists to make sure that this object is correctly constructed.

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

        } else {
            return;

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


}