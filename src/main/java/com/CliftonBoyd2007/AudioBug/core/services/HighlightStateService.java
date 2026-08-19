package com.CliftonBoyd2007.AudioBug.core.services;

import com.CliftonBoyd2007.AudioBug.core.LineHighlightLocator;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements facilities for obtaining information about highlights received from a {@link LineHighlightLocator} instance.
 * <p>
 * Copyright Clifton Boyd and AudioBug Contributors.
 * </p>
 *
 * @author Clifton Boyd
 */
@Service(Service.Level.PROJECT)
public final class HighlightStateService {
    /**
     * Backing store for error highlights.
     */
    private ArrayList<HighlightInfo> errors;
    /**
     * Backing store for warning highlights.
     */
    private ArrayList<HighlightInfo> warnings;

    /**
     * Constructor.
     * <p>
     * Please do not call this yourself.
     * This exists for the IntelliJ Platform so that it can construct this service when it is required.
     * To obtain this service elsewhere, use {@link Project#getService(Class)}.
     * </p>
     */
    public HighlightStateService() {

        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();

    }

    /**
     * Updates the list of errors and warnings.
     *
     * @param newErrors   The new list of error highlights.
     * @param newWarnings The new list of warning highlights.
     */
    public void updateHighlights(final ArrayList<HighlightInfo> newErrors, final ArrayList<HighlightInfo> newWarnings) {
        this.errors = newErrors;
        this.warnings = newWarnings;
    }

    /**
     * Returns an unmodifiable view of errors for the current line.
     * We do this to allow other parts of AudioBug to consume error/warning highlights without delegating the responsibility of ensuring immutability to those components.
     *
     * @return unmodifiable view of the list of error highlights.
     */
    public List<HighlightInfo> getErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    /**
     * Returns an unmodifiable view of warnings for the current line.
     * We do this to allow other parts of AudioBug to consume error/warning highlights without delegating the responsibility of ensuring immutability to those components.
     *
     * @return unmodifiable view of the list of warning highlights.
     */
    public List<HighlightInfo> getWarnings() {
        return Collections.unmodifiableList(this.warnings);
    }

}
