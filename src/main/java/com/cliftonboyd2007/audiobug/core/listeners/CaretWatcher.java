package com.cliftonboyd2007.audiobug.core.listeners;

import com.cliftonboyd2007.audiobug.core.AudioBug_Init;
import com.cliftonboyd2007.audiobug.core.services.highlightutils.LineHighlightLocator;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
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
public class CaretWatcher implements com.intellij.openapi.editor.event.CaretListener {
    /**
     * Queries for and collects error/warning highlights.
     */
    private LineHighlightLocator locator = new LineHighlightLocator();


    /**
     * Constructor.
     * <p>
     * Please DO NOT call this yourself. This is only here for {@link AudioBug_Init}.
     * </p>
     */
    public CaretWatcher() {



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
            locator.update(event);
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