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

    /**
     * Maps each {@link Cue} to its corresponding audio file in the JAR.
     * We use a map because adding cues does not require extra methods, and it also allows for O(1) lookup time on average.
     */
    private final HashMap<Cue, URL> audioResources = new HashMap<>();

    /**
     * Loads audio files into memory immediately upon construction so that they are always available to be played.
     */
    public CuePlayer() {

        loadAudioFiles();
    }

    /**
     * Establishes the mapping of {@link Cue} to their corresponding file's {@link URL}.
     */
    private void loadAudioFiles() {
        ClassLoader cl = getClass().getClassLoader();
        for (Cue cue : Cue.values()) {
            URL audioResource = cl.getResource(cue.getResourcePath());
            audioResources.put(cue, audioResource);
        }
    }


    /**
     * Plays the specified file with the Java Clip sound API.
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

    /**
     * Plays the given audio cue.
     *
     * @param cue the cue to be played
     * @see Cue
     */
    public void playCue(Cue cue) {
        play(this.audioResources.get(cue));


    }


}
