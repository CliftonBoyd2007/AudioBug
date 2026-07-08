package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import org.jetbrains.annotations.NotNull;

public class AudioBug_Init implements EditorFactoryListener {

    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        Editor editor = event.getEditor();
        CaretModel caretModel = editor.getCaretModel();
        caretModel.addCaretListener(new CaretWatcher());


    }
}
