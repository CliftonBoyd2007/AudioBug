package com.cliftonboyd2007.audiobug.audioutils;

import com.intellij.openapi.application.ApplicationInfo;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.net.URL;
import java.util.HashMap;


/**
 * Manages audio resource loading and playback.
 *
 * <p>
 * Audio resources are loaded immediately upon construction so that they are always available to play when requested.
 * </p>
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
     * Determines whether CuePlayer  is disabled.
     * <p>
     * In IntelliJ Idea 2026.3 (and other JetBrains IDEs version 2026.3), JetBrains implemented their own cues. Therefore, this is set to true if we are running a JetBrains IDE >= 2026.3 to avoid duplicated output.
     * </p>
     */
    private boolean isCuePlayerDisabled = false;

    /**
     * Loads audio files into memory immediately upon construction so that they are always available to be played.
     */
    public CuePlayer() {
        disableCuePlayerOnIDETooNew();
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

    private void disableCuePlayerOnIDETooNew() {
        ApplicationInfo applicationInfo = ApplicationInfo.getInstance();
        String ideMajorVersionString = applicationInfo.getMajorVersion();
        String ideMinorVersionString = applicationInfo.getMinorVersion();
        // We convert the IDE major version ot an Integer because we need to be able to use < and > operators.
        try {
            int ideMajorVersion = Integer.parseInt(ideMajorVersionString);
            int ideMinorVersion = Integer.parseInt(ideMinorVersionString);
            if ((ideMajorVersion > 2026) || (ideMajorVersion == 2026 && ideMinorVersion >= 3))
                this.isCuePlayerDisabled = true;

        } catch (NumberFormatException e) {
            // Disable them anyway. If we can't determine the version number, it's probably best to turn them off, in case we're running an EAP. The user can re-enable them manually if they need.
            this.isCuePlayerDisabled = true;
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
    private void play(@NotNull URL file) {
        if (isCuePlayerDisabled) return;
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
    public void playCue(@NotNull Cue cue) {
        play(this.audioResources.get(cue));


    }


}
