package com.CliftonBoyd2007.AudioBug.core;

import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.util.ui.accessibility.AccessibleAnnouncerUtil;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class WatchCaret implements CaretListener {
    private CuePlayer player;


    WatchCaret() {
        // Do not do anything here if you are not testing anything.
        // This only exists to make sure that this object is correctly constructed.
        this.player = new CuePlayer(); // REMOVE THIS BEFORE MOVING ON.
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
        try {
            this.player.playCue_Warning();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }


    }


}