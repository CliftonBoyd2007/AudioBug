package com.cliftonboyd2007.audiobug.breakpointutils;

import com.cliftonboyd2007.audiobug.core.services.FeedbackService;
import com.intellij.openapi.components.Service;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.XBreakpointListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Project-level service for querying breakpoint state on the current line of the caret.
 * <p>
 * It provides user-facing feedback when breakpoints are created and removed, as well as allowing the user to determine whether a breakpoint exists on the current line of the caret.
 * </p>
 *
 * @author Clifton Boyd
 */
@Service(Service.Level.PROJECT)
public final class BreakpointObserverService implements XBreakpointListener {
    /**
     * The project from which we obtain {@link FeedbackService}.
     */
    private final Project project;

    /**
     * Constructor.
     * <p>
     * Please do not call this yourself.
     * This exists for the IntelliJ Platform so that it can construct this service when it is required.
     * To obtain this service elsewhere, use {@link Project#getService(Class)}.
     * </p>
     *
     * @param project the project the user is currently working with
     */
    public BreakpointObserverService(Project project) {
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
