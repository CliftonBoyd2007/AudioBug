package com.cliftonboyd2007.audiobug.core.services.caretutils;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.PROJECT)
public final class CaretStateService {
    /**
     * The line number of the line the caret is currently located on.
     */
    private int currentLineNum;
    /**
     * The offset at which the caret is positioned. This is NOT the same as the caret's line offset.
     */
    private int caretOffset;

    private Document document;

    private final LineOffsetRange lineOffsetRange = new LineOffsetRange();

    public CaretStateService() {
    }

    public void updateWithCaretEvent(@NotNull CaretEvent event) {
        updateDocument(event.getEditor().getDocument());
        this.currentLineNum = event.getNewPosition().line;
        updateLineOffsets();
    }

    /**
     * Updates the stored start and end offsets for the caret's current line.
     *
     */
    private void updateLineOffsets() {

        int startOffset = this.document.getLineStartOffset(this.currentLineNum);
        int endOffset = this.document.getLineEndOffset(this.currentLineNum);
        lineOffsetRange.updateOffsets(startOffset, endOffset);
    }

    /**
     * Updates the document instance field.
     *
     * @param newDocument the document the caret is located in
     */
    private void updateDocument(@NotNull final Document newDocument) {
        this.document = newDocument;
    }

    /**
     * Updates the current line number instance field.
     *
     * @param newLineNum the line number of the line that the caret has entered
     */
    public void updateCurrentLine(final int newLineNum) {
        this.currentLineNum = newLineNum;
    }

    /**
     * @return the current line number displayed to the user
     * currentLineNum is incremented because the line number obtained from {@link LogicalPosition#line} is 0based.
     * Incrementing currentLineNum ensures that line numbers reported in the editor UI and the line number reported by AudioBug will match.
     */
    public int getUserFacingLineNum() {
        return this.currentLineNum + 1;
    }

    /**
     * @return the 0-based line number used internally by the editor
     */
    public int getCurrentLineNum() {
        return this.currentLineNum;
    }
}
