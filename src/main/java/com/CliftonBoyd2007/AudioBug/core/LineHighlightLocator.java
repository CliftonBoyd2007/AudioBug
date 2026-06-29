package com.CliftonBoyd2007.AudioBug.core;

import java.util.ArrayList;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.util.Processor;
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;

import com.intellij.openapi.project.Project;

import org.jetbrains.annotations.NotNull;

public class LineAnnotationLocator {
    private record LineOffsets(int startOffset, int endOffset) {
    }

    /**
     * Record containing the start and end offsets for the current line of the caret.
     */
    private LineOffsets lineOffsets;
    private ArrayList<HighlightInfo> highlights;

    public LineAnnotationLocator(@NotNull CaretEvent event) {
        highlights = new ArrayList<>();
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
     * Updates the line offsets field when the caret is moved to a new line.
     *
     * @param event The event containing information about the caret.
     */
    public void update(@NotNull CaretEvent event) {
        this.lineOffsets = getLineOffsets(event);
    }

    /**
     * Adds highlights to a backing store for on-demand retrieval by the user.
     *
     * @param document The document from which highlights are requested.
     * @param project  The project from which the document originates.
     */
    private void getHighlightHelper(Document document, Project project) {


        DaemonCodeAnalyzerEx.processHighlights(document, project, HighlightSeverity.WARNING, lineOffsets.startOffset, lineOffsets.endOffset, Processor < HighlightInfo > processor, info -> {
            this.highlights.add(info);
            return true;
        });
    }


}