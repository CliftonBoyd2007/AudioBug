package com.CliftonBoyd2007.AudioBug.audioutils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.net.URL;

/**
 * Facilitates audio file playback for error, warning, and breakpoint cues.
 * Copyright Clifton Boyd and AudioBug Contributors.
 *
 * @author Clifton Boyd
 */
public class CuePlayer {

    /**
     * The cue to be played when an error is encountered.
     */
    private URL cue_Error;
    /**
     * The cue to be played when a breakpoint is encountered.
     */
    private URL cue_Breakpoint;
    /**
     * The cue to be played when a warning is encountered.
     */
    private URL cue_Warning;
    /**
     * The cue to be played when AudioBug starts.
     */
    private URL cue_Start;
    /**
     * Indicates whether  all audio files are available.
     */
    private boolean allFilesAvailable;

    /**
     * Constructor.
     */
    public CuePlayer() {
        loadAudioResources();
        this.allFilesAvailable = verifyFilesAvailable();
    }

    /**
     * Loads audio files into CuePlayer.
     */
    private void loadAudioResources() {
        ClassLoader cl = getClass().getClassLoader();
        this.cue_Breakpoint = cl.getResource("Sounds/Breakpoint.wav");
        this.cue_Error = cl.getResource("Sounds/Error.wav");
        this.cue_Warning = cl.getResource("Sounds/Warning.wav");
        this.cue_Start = cl.getResource("Sounds/AudioBug_Started.wav");
    }

    /**
     * @return true if all URLs are not null.
     */
    private boolean verifyFilesAvailable() {
        if (this.cue_Breakpoint == null || this.cue_Error == null || this.cue_Warning == null) {
            return false;
        }
        return this.cue_Breakpoint != null
                && this.cue_Error != null
                && this.cue_Warning != null;

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
        } catch (LineUnavailableException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * Plays the cue for errors.
     */
    public void playCue_Error() {
        play(this.cue_Error);
    }

    /**
     * Plays the cue for breakpoints.
     */
    public void playCue_Breakpoint() {
        play(this.cue_Breakpoint);

    }

    /**
     * Plays the cue for warnings.
     */
    public void playCue_Warning() {
        play(this.cue_Warning);
    }

    /**
     * Plays the cue for AudioBug startup.
     */
    public void playCue_Start() {
        play(this.cue_Start);
    }

    /**
     * Getter for {@link this#allFilesAvailable}.
     *
     * @return {@link this#allFilesAvailable}.
     */
    public boolean getAllFilesAvailable() {
        return this.allFilesAvailable;
    }
}
