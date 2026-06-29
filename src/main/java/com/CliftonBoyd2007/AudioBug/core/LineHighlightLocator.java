package com.CliftonBoyd2007.AudioBug.core;

import java.util.ArrayList;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;

public class LineHighlightLocator {
    private record LineOffsets(int startOffset, int endOffset) {
    }

    /**
     * The document the user is currently working with.
     */
    private Document document;
    /**
     * The project in which the current document is located.
     */
    private final Project project;

    /**
     * Record containing the start and end offsets for the current line of the caret.
     */
    private LineOffsets lineOffsets;

    /**
     * Cache backing store for {@link HighlightInfo} objects.
     * It must be cleared when the caret moves to a new line because the information within the list refer to errors and/or warnings from the previous line of the caret.
     */
    private ArrayList<HighlightInfo> highlights;

    public LineHighlightLocator(@NotNull CaretEvent event) {
        this.highlights = new ArrayList<>();
        update(event);
        this.project = event.getEditor().getProject();
        this.document = event.getEditor().getDocument();


    }

    /**
     * Gets the start and end offsets for the caret's current line.
     *
     * @param event Event containing relevant information about the caret.
     * @return start and end offsets for the line of the caret.
     */
    private LineOffsets getLineOffsets(@NotNull CaretEvent event) {
        int line = event.getNewPosition().line;
        int startOffset = this.document.getLineStartOffset(line);
        int endOffset = this.document.getLineEndOffset(line);
        return new LineOffsets(startOffset, endOffset);
    }

    /**
     * Updates line offsets when the caret is moved to a new line.
     *
     * @param event The event containing information about the caret.
     */
    public void update(@NotNull CaretEvent event) {
        this.lineOffsets = getLineOffsets(event);
        this.highlights.clear();
        if (hasDocumentChanged(this.document, event.getEditor().getDocument())) {
            this.document = event.getEditor().getDocument();
        }
    }

    /**
     * Indicates whether the current {@link Document} has changed.
     *
     * @param oldDocument The previous document in which the user was working.
     * @param newDocument The document the user has moved to.
     * @return true if the document has changed.
     */
    private boolean hasDocumentChanged(Document oldDocument, Document newDocument) {
        return oldDocument.equals(newDocument);
    }

    /**
     * Primitive storage of highlights for the current line.
     * This may or may not be used in a release version; we have to test it first.
     * The API that I am using to obtain {@link HighlightInfo} objects {@link DaemonCodeAnalyzerEx#processHighlights(Document, Project, HighlightSeverity, int, int, Processor)}, is marked as experimental, but it was the only API that exposed the information that AudioBug requires.
     * This is acknowledgement that this may break in the future.
     */
    private void getHighlightHelper() {
        Processor<HighlightInfo> highlightProcessor = (HighlightInfo info) -> {
            this.highlights.add(info);
            return true;
        };

        DaemonCodeAnalyzerEx.processHighlights(this.document, this.project, HighlightSeverity.WARNING, lineOffsets.startOffset, lineOffsets.endOffset, highlightProcessor);
    }


}