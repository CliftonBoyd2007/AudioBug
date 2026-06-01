package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.CaretEvent;
import org.jetbrains.annotations.NotNull;

public class LineAnnotationLocater {
    private record LineOffsets(int startOffset, int endOffset) {}

    /**
     * Record containing the start and end offsets for the current line.
     */
    private LineOffsets lineOffsets;


    public LineAnnotationLocater() {

        this.lineOffsets = getLineOffsets(event.getNewPosition().line);


    }

    /**
     * Returns a record containing the start and end offsets for the current line.
     *
     * @param line The line of the caret.
     * @return Start and end offsets for the current line.
     */
    private LineOffsets getLineOffsets(int line) {
        Document document = this.event.getEditor().getDocument();
        int startOffset = document.getLineStartOffset(line);
        int endOffset = document.getLineEndOffset(line);
        return new LineOffsets(startOffset, endOffset);
    }

    public void update(@NotNull CaretEvent event) {
        

    }

}
