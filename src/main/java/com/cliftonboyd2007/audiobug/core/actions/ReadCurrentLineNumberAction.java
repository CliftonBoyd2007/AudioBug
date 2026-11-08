package com.cliftonboyd2007.audiobug.core.actions;

import com.cliftonboyd2007.audiobug.core.services.caretutils.CaretStateService;
import com.cliftonboyd2007.audiobug.core.services.accessibility.FeedbackService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Reads the current line number of the caret with the active screen reader.
 * <p>
 * <p>
 * This action can be invoked by pressing Ctrl + Alt + Shift + G on Windows, and Ctrl + Option + Shift + G on macOS.
 * Note that Ctrl-Option is the VoiceOver modifier, so the user must press Ctrl + Option + Tab before pressing Ctrl + Option + Shift + G to make VoiceOver pass the shortcut through to the IDE.
 *
 * </p>
 * <p>
 * User-facing output example: "Line 81"
 * </p>
 *
 * @author Clifton Boyd
 */
public class ReadCurrentLineNumberAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        assert project != null;
        CaretStateService caretStateService = project.getService(CaretStateService.class);
        FeedbackService feedbackService = project.getService(FeedbackService.class);
        feedbackService.announce(String.format("Line %d", caretStateService.getUserFacingLineNum()), true);
    }
}
