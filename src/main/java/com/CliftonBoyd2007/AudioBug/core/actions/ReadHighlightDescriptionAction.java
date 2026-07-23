package com.CliftonBoyd2007.AudioBug.core.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.CliftonBoyd2007.AudioBug.core.services.HighlightStateService;
import com.CliftonBoyd2007.AudioBug.core.services.FeedbackService;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;

public class ReadHighlightDescriptionAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {

        FeedbackService feedbackService = event.getProject().getService(FeedbackService.class);
        HighlightStateService highlightService = event.getProject().getService(HighlightStateService.class);

    }


}
