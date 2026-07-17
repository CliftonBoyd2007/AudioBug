package com.CliftonBoyd2007.AudioBug.audioutils;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;


public class CuePlayer {

    /**
     * The cue to be played when an error is encountered.
     */
    private final File cue_Error;
    /**
     * The cue to be played when a breakpoint is encountered.
     */
    private final File cue_Breakpoint;
    /**
     * The cue to be played when a warning is encountered.
     */
    private final File cue_Warning;
    /**
     * Indicates whether  all audio files are available.
     */
    private boolean allFilesAvailable;

    /**
     * Constructor.
     *
     * @param cue_Breakpoint The audio file to be played for breakpoints.
     * @param cue_Error      The audio file to be played for errors.
     * @param cue_Warning    The audio file to be played for warnings.
     */
    public CuePlayer(final File cue_Breakpoint, final File cue_Error, final File cue_Warning) {
        this.cue_Breakpoint = cue_Breakpoint;
        this.cue_Error = cue_Error;
        this.cue_Warning = cue_Warning;
        this.allFilesAvailable = verifyFilesAvailable();
    }

    /**
     * @return true if all files exist and are not null.
     */
    private boolean verifyFilesAvailable() {
        if (this.cue_Breakpoint == null || this.cue_Error == null || this.cue_Warning == null) {
            return false;
        }
        return this.cue_Breakpoint.exists()
                && this.cue_Error.exists()
                && this.cue_Warning.exists();
    }

    /**
     * Plays the specified file with the Java Clip sound API.
     * If any exception is thrown, audio playback will be disabled, regardless of the availability of the audio files.
     *
     * @param file the audio file to be played.
     * @throws IOException                   When a file I/O error occurs.
     * @throws UnsupportedAudioFileException When an unsupported file is specified.
     * @throws LineUnavailableException      When a {code Line} is unavailable.

     * @see javax.sound.sampled.LineUnavailableException
     *
     */
    private void play(File file) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (LineUnavailableException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            System.err.println(e.getMessage());
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
     * Getter for {@link this#allFilesAvailable}.
     *
     * @return {@link this#allFilesAvailable}.
     */
    public boolean getAllFilesAvailable() {
        return this.allFilesAvailable;
    }
}
