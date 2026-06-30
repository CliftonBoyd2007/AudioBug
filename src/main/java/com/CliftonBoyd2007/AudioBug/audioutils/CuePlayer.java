package com.CliftonBoyd2007.AudioBug.audioutils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioBugCuePlayer {
    /**
     * The cue to be played when the caret enters a line with an error.
     */
    private final File cue_Error;
    /**
     * The cue to be played when the caret enters a line with a warning.
     */
    private final File cue_Warning;
    /**
     * The cue to be played when the caret enters a line with a breakpoint.
     */
    private final File cue_Breakpoint;
    /**
     * Global flag that determines whether audio cues will be played.
     */
    private boolean allFilesAvailable;

    public AudioBugCuePlayer(File cue_Breakpoint, File cue_Error, File cue_Warning) {
        this.cue_Breakpoint = cue_Breakpoint;
        this.cue_Error = cue_Error;
        this.cue_Warning = cue_Warning;
        this.allFilesAvailable = verifyAllFilesExist();

    }

    /**
     * Indicates whether all specified audio files are available.
     *
     * @return Whether all files are available to play.
     */
    private boolean verifyAllFilesExist() {
        return this.cue_Breakpoint.exists()
                && this.cue_Error.exists()
                && this.cue_Warning.exists();
    }

    /**
     * Plays the specified audio file using the Java Clip sound API.
     * Any print statements are for debugging purposes only.
     *
     * @param file The file to be played.
     * @throws UnsupportedAudioFileException When an unsupported audio file is passed into the method. Audio playback will be disabled.
     * @throws IOException                   When any file I/O error occurs. Audio playback will be disabled.
     * @throws LineUnavailableException      When an audio line (or audio output) is either busy or unavailable. Audio file playback will be disabled.
     */

    private void play(final File file) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();


        } catch (UnsupportedAudioFileException ex) {
            System.err.println("Unsupported file: " + file);
            System.err.println(ex.getMessage());
            // Avoid audio playback to prevent inconsistent feedback.

            allFilesAvailable = false;
        } catch (LineUnavailableException ex) {
            System.err.println("Audio line unavailable.");
            System.err.println(ex.getMessage());

            allFilesAvailable = false;
        } catch (IOException ex) {
            System.err.println("I/O error occurred.");
            System.err.println(ex.getMessage());

            allFilesAvailable = false;
        } catch (Exception ex) {
            System.err.println("Unknown error occurred.");
            System.err.println(ex.getMessage());

            allFilesAvailable = false;
        }


    }

    /**
     * Play the cue for warnings.
     */
    public void playCue_Warning() {
        play(this.cue_Warning);


    }

    /**
     * Play the cue for errors.
     */

    public void playCue_Error() {
        play(this.cue_Error);
    }

    /**
     * Play the cue for breakpoints.
     */

    public void playCue_Breakpoint() {
        play(this.cue_Breakpoint);

    }

    /**
     * @return the value of {@link this#allFilesAvailable}.
     */
    public boolean getAudioFilesAvailable() {
        return this.allFilesAvailable;
    }


}