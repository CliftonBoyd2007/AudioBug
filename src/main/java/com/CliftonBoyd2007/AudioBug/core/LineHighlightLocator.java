package com.CliftonBoyd2007.AudioBug.core;


import java.util.ArrayList;

import com.CliftonBoyd2007.AudioBug.core.services.HighlightStateService;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.CliftonBoyd2007.AudioBug.accessibility.HighlightAnnouncerService;

/**
 * Responsible for querying highlights for the current line of the caret for AudioBug to announce.
 * <p>
 * Copyright Clifton Boyd and AudioBug Contributors.
 * </p>
 *
 * @author Clifton Boyd
 */
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
     * Backing store for error highlights.
     */
    private final ArrayList<HighlightInfo> errors = new ArrayList<>();

    /**
     * Backing store for warning highlights.
     */
    private final ArrayList<HighlightInfo> warnings = new ArrayList<>();


    public LineHighlightLocator(@NotNull CaretEvent event) {

        this.project = event.getEditor().getProject();
        update(event);

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
     * Updates and removes stale information when the caret moves to a new line.
     *
     * @param event The event containing information about the caret.
     */
    public void update(@NotNull CaretEvent event) {
        this.lineOffsets = getLineOffsets(event);
        updateDocument(event.getEditor().getDocument());
        // Clear the lists before retrieving new highlights to avoid retaining stale highlights.
        clearErrorAndWarningLists();

        getHighlights();
        HighlightStateService highlightService = this.project.getService(HighlightStateService.class);
        highlightService.updateHighlights(this.errors, this.warnings);
        HighlightAnnouncerService announcerService = this.project.getService(HighlightAnnouncerService.class);
        announcerService.updateService();
    }


    /**
     * Clears the error and warning backing stores.
     */
    private void clearErrorAndWarningLists() {
        this.errors.clear();
        this.warnings.clear();

    }


    /**
     * Updates the document instance field.
     * We must do this to ensure that we are not pulling from a stale Document when the end-user moves from one to another.
     *
     * @param newDocument The document the user has moved to.
     */
    private void updateDocument(Document newDocument) {

        this.document = newDocument;
    }


    /**
     * Primitive storage of highlights for the current line.
     * <p>
     * AudioBug currently ignores any highlights below
     * {@link HighlightSeverity#WARNING}.
     *
     * <p>This implementation currently relies on
     * {@link DaemonCodeAnalyzerEx#processHighlights(Document, Project,
     * HighlightSeverity, int, int, Processor)} because it is the only API
     * that exposes the {@link HighlightInfo} objects required by AudioBug.
     *
     * <p>Note:
     * {@code processHighlights()} is currently marked
     * {@code @ApiStatus.Experimental}. Future IntelliJ Platform releases
     * may replace or remove this API. If a stable replacement becomes
     * available, this method should be updated accordingly.
     */
    private void getHighlights() {
        Processor<HighlightInfo> highlightProcessor = (HighlightInfo info) -> {
            if (info.getSeverity() == HighlightSeverity.WARNING) {
                this.warnings.add(info);
            } else if (info.getSeverity() == HighlightSeverity.ERROR) {
                this.errors.add(info);
            }
            return true;
        };

        DaemonCodeAnalyzerEx.processHighlights(this.document, this.project, HighlightSeverity.WARNING, lineOffsets.startOffset, lineOffsets.endOffset, highlightProcessor);
    }


}