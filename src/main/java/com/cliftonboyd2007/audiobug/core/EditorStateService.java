package com.cliftonboyd2007.audiobug.core;

import com.intellij.openapi.components.Service;

@Service(Service.Level.PROJECT)
public final class EditorStateService {
    /**
     * The line number of the line the caret is currently located on.
     */
    private int currentLineNum;

    public EditorStateService() {
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
     * @return the current line number
     */
    public int getCurrentLineNum() {
        return this.currentLineNum + 1;
    }
}
