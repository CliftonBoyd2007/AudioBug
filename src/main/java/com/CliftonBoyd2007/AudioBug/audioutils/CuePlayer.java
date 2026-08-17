package com.CliftonBoyd2007.AudioBug.audioutils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.net.URL;
import java.util.HashMap;


/**
 * Facilitates audio file playback for error, warning, and breakpoint cues.
 * Copyright Clifton Boyd and AudioBug Contributors.
 *
 * @author Clifton Boyd
 */
public class CuePlayer {


    private final HashMap<Cue, URL> audioResources = new HashMap<>();

    /**
     * Constructor.
     */
    public CuePlayer() {

        loadAudioFiles();
    }

    private void loadAudioFiles() {
        ClassLoader cl = getClass().getClassLoader();
        for (Cue cue : Cue.values()) {
            URL audioResource = cl.getResource(cue.getResourcePath());
            audioResources.put(cue, audioResource);
        }
    }


    /**
     * Plays the specified file with the Java Clip sound API.
     * If any exception is thrown, audio playback will be disabled, regardless of the availability of the audio files.
     *
     * @param file the audio file to be played.
     * @throws UnsupportedAudioFileException When an unsupported file is specified.
     * @throws LineUnavailableException      When a {code Line} is unavailable.
     * @see javax.sound.sampled.LineUnavailableException
     *
     */
    private void play(URL file) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void playCue(Cue cue) {
        play(this.audioResources.get(cue));


    }


}
