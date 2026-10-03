package com.cliftonboyd2007.audiobug.core.services.editorutils;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.PROJECT)
public final class EditorStateService {
    /**
     * The line number of the line the caret is currently located on.
     */
    private int currentLineNum;





    public EditorStateService() {}

    public void update(@NotNull CaretEvent event) {


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
     * @return the current user-facing line number.
     * currentLineNum is incremented because the line number obtained from {@link LogicalPosition#line} is 0-based, and the UI displays line numbers starting from 1.
     * Incrementing currentLineNum makes it so that the line number AudioBug reports and the line number shown in the editor match.
     */
    public int getCurrentUserFacingLineNum() {
        return this.currentLineNum + 1;
    }

    /**
     * @return the 0-based line number used internally by the editor
     * This line number is not shown to the user.
     */
    public int getCurrentLineNum() {
return this.currentLineNum;
    }
}
