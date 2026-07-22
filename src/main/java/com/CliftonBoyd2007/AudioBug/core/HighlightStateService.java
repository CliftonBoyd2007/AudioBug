package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements facilities for obtaining information about highlights received from a {@link LineHighlightLocator} instance.
 *
 * @author Clifton Boyd
 */
@Service(Service.Level.PROJECT)
public final class HighlightStateService {
    private ArrayList<HighlightInfo> errors;
    private ArrayList<HighlightInfo> warnings;
    private final Project project;

    public HighlightStateService(Project project) {
        this.project = project;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();

    }

    public void updateHighlights(final ArrayList<HighlightInfo> newErrors, final ArrayList<HighlightInfo> newWarnings) {
        this.errors = newErrors;
        this.warnings = newWarnings;
    }

    /**
     * Returns an unmodifiable copy of errors for the current line.
     * @return unmodifiable list of error highlights.
     */
    public List<HighlightInfo> getErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    /**
     * Returns an unmodifiable copy of warnings for the current line.
     * @return unmodifiable list of warning highlights.
     */
    public List<HighlightInfo> getWarnings() {
        return Collections.unmodifiableList(this.warnings);
    }

}
