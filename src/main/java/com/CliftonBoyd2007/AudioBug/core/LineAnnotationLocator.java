package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretEvent;

import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import org.jetbrains.annotations.NotNull;

public class LineAnnotationLocator {
    private record LineOffsets(int startOffset, int endOffset) {
    }

    /**
     * Record containing the start and end offsets for the current line of the caret.
     */
    private LineOffsets lineOffsets;

    public LineAnnotationLocator(@NotNull CaretEvent event) {

        update(event);


    }

    /**
     * Gets the start and end offsets for the caret's current line.
     *
     * @param event Event containing the caret's current position and editor.
     * @return start and end offsets for the line of the caret.
     */
    private LineOffsets getLineOffsets(@NotNull CaretEvent event) {
        int line = event.getNewPosition().line;
        Document document = event.getEditor().getDocument();
        int startOffset = document.getLineStartOffset(line);
        int endOffset = document.getLineEndOffset(line);
        return new LineOffsets(startOffset, endOffset);
    }

    /**
     * Updates the line offsets field when the caret is moved.
     *
     * @param event The event containing information about the caret.
     */
    public void update(@NotNull CaretEvent event) {
        this.lineOffsets = getLineOffsets(event);
    }


    /**
     * This is simply a playground. Remove this in public code.
     */

    private void playground(@NotNull CaretEvent event) {

    }


}