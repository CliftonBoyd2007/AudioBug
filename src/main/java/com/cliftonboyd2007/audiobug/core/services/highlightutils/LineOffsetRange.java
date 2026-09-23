package com.cliftonboyd2007.audiobug.core.services.highlightutils;

/**
 * Represents a given range of text in the editor.
 *
 * @author Clifton Boyd
 */
public class LineOffsetRange {

    int startOffset;
    int endOffset;

    LineOffsetRange() {
        // Please do not do anything in here. This constructor exists to make sure this object is not null.
    }

    /**
     * Updates the stored line offsets.
     *
     * @param newStartOffset The new starting offset of the caret's current line
     * @param newEndOffset   the new ending offsets of the caret's current line.
     */
    void updateOffsets(final int newStartOffset, final int newEndOffset) {
        this.startOffset = newStartOffset;
        this.endOffset = newEndOffset;
    }
}
