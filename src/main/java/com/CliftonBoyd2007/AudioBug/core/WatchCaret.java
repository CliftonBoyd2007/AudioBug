package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;


import org.jetbrains.annotations.NotNull;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;

import java.io.File;


public class WatchCaret implements CaretListener {
    private CuePlayer player;


    WatchCaret() {
        // Unless you are testing something, leave this method alone.
        // This only exists to make sure that this object is correctly constructed.
        this.player = new CuePlayer();
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        LogicalPosition oldPosition = event.getOldPosition();
        LogicalPosition newPosition = event.getNewPosition();
        boolean caretHasMovedToNewLine = hasCaretMovedToNewLine(oldPosition, newPosition);
        if (caretHasMovedToNewLine) {
            this.player.playCue_Breakpoint();
        } else {
            this.player.playCue_Warning();
        }
    }

    /**
     * Indicates whether the caret has moved to a new line.
     * @param oldPosition The previous position of the caret.
     * @param newPosition The current position of the caret.
     * @return true if the line of the caret has changed.
     */
    private boolean hasCaretMovedToNewLine(LogicalPosition oldPosition, LogicalPosition newPosition) {
        return oldPosition.line != newPosition.line;
    }


}