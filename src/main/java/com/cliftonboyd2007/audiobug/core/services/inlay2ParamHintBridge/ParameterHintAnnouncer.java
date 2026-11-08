package com.cliftonboyd2007.audiobug.core.services.inlay2ParamHintBridge;

import com.cliftonboyd2007.audiobug.core.services.inlayBridge.InlayListener;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.InlayModel;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

class ParameterHintAnnouncerUtil {
    private final Project project;
    private final InlayListener inlayListener = new InlayListener();
    private InlayModel editorInlayModel;
    private Editor editor;

    public ParameterHintAnnouncerUtil(@NotNull final Editor editor) {
        this.editor = editor;
        this.editorInlayModel = editor.getInlayModel();
        this.project = editor.getProject();

    }



}
