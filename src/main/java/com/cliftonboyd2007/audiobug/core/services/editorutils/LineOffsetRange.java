package com.cliftonboyd2007.audiobug.core.services.editorutils;

/**
 * Represents a given range of text in the editor.
 *
 * @author Clifton Boyd
 */
public class LineOffsetRange {

    public int startOffset;
    public int endOffset;

    public LineOffsetRange() {
        // Please do not do anything in here. This constructor exists to make sure this object is not null.
    }

    /**
     * Updates the stored line offsets.
     *
     * @param newStartOffset The new starting offset of the caret's current line
     * @param newEndOffset   the new ending offsets of the caret's current line.
     */
    public void updateOffsets(final int newStartOffset, final int newEndOffset) {
        this.startOffset = newStartOffset;
        this.endOffset = newEndOffset;
    }
}
