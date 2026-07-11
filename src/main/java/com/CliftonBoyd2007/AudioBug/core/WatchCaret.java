package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class WatchCaret implements CaretListener {


    WatchCaret() {
        // Do not do anything here.
        // This only exists to make sure that this object is correctly constructed.
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {

        AccessibleAnnouncerUtil.announce(event.getEditor().getComponent().getAccessibleContext().getAccessibleParent(), "Caret has moved.", true);

    }


}