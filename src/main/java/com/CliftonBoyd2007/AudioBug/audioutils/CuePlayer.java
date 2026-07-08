package com.CliftonBoyd2007.AudioBug.audioutils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class CuePlayer {
    /**
     * The cue to be played when the caret enters a line with an error.
     */
    private File cue_Error;
    /**
     * The cue to be played when the caret enters a line with a warning.
     */
    private File cue_Warning;
    /**
     * The cue to be played when the caret enters a line with a breakpoint.
     */
    private File cue_Breakpoint;
    /**
     * Global flag that determines whether audio cues will be played.
     */
    private boolean allFilesAvailable;

    public CuePlayer() {
        loadAudioFiles();

    }

    /**
     * Loads audio files into CuePlayer.
     */
    private void loadAudioFiles() {
        ClassLoader cl = this.getClass().getClassLoader();
        try {
            this.cue_Breakpoint = new File(Objects.requireNonNull(cl.getResource("/Sounds/Breakpoint.wav")).toURI());
            this.cue_Error = new File(Objects.requireNonNull(cl.getResource("/Sounds/Error.wav")).toURI());
            this.cue_Warning = new File(Objects.requireNonNull(cl.getResource("/Sounds/Warning.wav")).toURI());
            this.allFilesAvailable = verifyAllFilesExist();
        } catch (URISyntaxException e) {
            // Log that we have disabled audio playback in the event that the files fail to load.
            this.allFilesAvailable = false;
        } catch (NullPointerException e) {
            // Disable audio playback. Log it and go away.
            this.allFilesAvailable = false;
        } catch (Exception e) {
            this.allFilesAvailable = false;
        }


    }

    /**
     * Indicates whether all specified audio files are available.
     *
     * @return true if all files exist and are not null.
     */
    private boolean verifyAllFilesExist() {
        if (this.cue_Breakpoint == null || this.cue_Error == null || this.cue_Warning == null) {
            return false;
        }
        return this.cue_Breakpoint.exists()
                && this.cue_Error.exists()
                && this.cue_Warning.exists();
    }

    /**
     * Plays the specified audio file using the Java Clip sound API.
     * Any print statements are for debugging purposes only.
     * If any exception is thrown, {@code this#allFilesAvailable} will be marked false.
     *
     * @param file The file to be played.
     * @throws UnsupportedAudioFileException When an unsupported audio file is passed into the method.
     * @throws IOException                   When any file I/O error occurs.
     * @throws LineUnavailableException      When an audio line (or audio output) is either busy or unavailable.
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
     * Any exceptions thrown here are caught in {@link this#play(File)}. Catching them here is unnecessary.
     */
    public void playCue_Warning() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        play(this.cue_Warning);


    }

    /**
     * Play the cue for errors.
     */

    public void playCue_Error() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        play(this.cue_Error);
    }

    /**
     * Play the cue for breakpoints.
     */

    public void playCue_Breakpoint() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        play(this.cue_Breakpoint);

    }

    /**
     * @return the value of {@link this#allFilesAvailable}.
     */
    public boolean getAllFilesAvailable() {
        return this.allFilesAvailable;
    }


}