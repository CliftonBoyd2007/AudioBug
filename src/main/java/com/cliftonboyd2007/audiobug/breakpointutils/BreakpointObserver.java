package com.cliftonboyd2007.audiobug.breakpointutils;

import com.cliftonboyd2007.audiobug.core.services.FeedbackService;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.XBreakpointListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class BreakpointObserver implements XBreakpointListener {
    private final Project project;

    public BreakpointObserver(Project project) {
        this.project = project;
    }

    /**
     * Produces auditory and spoken feedback when a breakpoint is added to the current line.
     *
     * @param breakpoint the breakpoint that was added to the current line
     */
    @Override
    public void breakpointAdded(@NotNull XBreakpoint breakpoint) {
        // Play breakpoint cue
        // Announce "Breakpoint Added, Line n," where n is the line number
    }

    /**
     * Produces spoken feedback when a breakpoint is removed from the current line.
     *
     * @param breakpoint the breakpoint that was removed
     */
    @Override
    public void breakpointRemoved(@NotNull XBreakpoint breakpoint) {
        // Only announce "Breakpoint removed, Line n," where n is the line number
    }

    /**
     * Produces spoken feedback indicating whether a breakpoint is present on the current line.
     */
    public void queryForBreakpointsOnCurrentLine() {
        // If there are breakpoints, announce "Breakpoint on line."
        // Otherwise, announce "No breakpoints."
    }
}
