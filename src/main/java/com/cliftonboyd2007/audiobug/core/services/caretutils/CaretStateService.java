package com.cliftonboyd2007.audiobug.core.services.caretutils;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.PROJECT)
public final class CaretStateService {
    /**
     * The line number of the line the caret is currently located on.
     */
    private int currentLineNum;
    /**
     * The offset at which the caret is currently positioned. This is NOT the same as the line offsets of the line the caret is on.
     */
    private int caretOffset;

    /**
     * The document the user is working with
     */
    private Document document;

    /**
     * The start and end offsets for the current line of the caret.
     */
    private final LineOffsetRange lineOffsetRange = new LineOffsetRange();


    /**
     * Constructor.
     * <p>
     * Please do not call this yourself.
     * This exists for the IntelliJ Platform so that it can construct this service when it is required.
     * To obtain this service elsewhere, use {@link Project#getService(Class)}.
     * </p>
     */
    public CaretStateService() {
    }

    public void update(@NotNull CaretEvent event) {
        updateDocument(event.getEditor().getDocument());
        this.currentLineNum = event.getNewPosition().line;
        updateCaretOffset(event);
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
     * Updates the stored offset of the current caret.
     * <p>
     * The offset tells us exactly where the caret is located in the editor. This is useful for many things, particularly if we are looking for something at the caret's current location.
     * </p>
     *
     * @param event the event resulting from caret movement
     */
    private void updateCaretOffset(@NotNull CaretEvent event) {
        Caret currentCaret = event.getCaret();
        this.caretOffset = currentCaret.getOffset();
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

    /**
     * @return the offset of the current caret
     */
    public int getCaretOffset() {
        return this.caretOffset;
    }

    /**
     * @return the line offsets for the current line
     */
    public LineOffsetRange getLineOffsets() {
        return this.lineOffsetRange;
    }
}
