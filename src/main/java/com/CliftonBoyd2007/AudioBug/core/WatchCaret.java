package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;

import org.jetbrains.annotations.NotNull;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;

import java.io.File;


public class WatchCaret implements CaretListener {
    private CuePlayer player;


    WatchCaret() {
        // Unless you are testing something, leave this method alone.
        // This only exists to make sure that this object is correctly constructed.
        this.player = new CuePlayer(new File("C:\\Users\\Clifton Boyd\\Document\s\w\av\Breakpoint.wav"), new File("C:\\Users\\Clifton Boyd\\Documents\\wav\\Error.wav"), "C:\\Users\\Clifton Boyd\\Documents\\wav\\Warning.wav"); // REMOVE THIS BEFORE MOVING ON.
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        this.player.playCue_Breakpoint();
    }


}