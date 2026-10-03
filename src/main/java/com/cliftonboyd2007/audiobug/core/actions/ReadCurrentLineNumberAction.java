package com.cliftonboyd2007.audiobug.core.actions;

import com.cliftonboyd2007.audiobug.core.services.editorutils.EditorStateService;
import com.cliftonboyd2007.audiobug.core.services.accessibility.FeedbackService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ReadCurrentLineNumberAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        assert project != null;
        EditorStateService editorStateService = project.getService(EditorStateService.class);
        FeedbackService feedbackService = project.getService(FeedbackService.class);
        feedbackService.announce(String.format("Line %d", editorStateService.getCurrentUserFacingLineNum()), true);
    }
}
