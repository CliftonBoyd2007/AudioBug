
package com.cliftonboyd2007.audiobug.audioutils;

/**
 * Represents each sounde cue that can be played in AudioBug.
 */
public enum Cue {
    /**
     * The cue to be played when the caret enters a line with a breakpoint.
     */
    BREAKPOINT("Sounds/Breakpoint.wav"),
    /**
     * The cue to be played when the caret enters a line with an error.
     */
    ERROR("Sounds/Error.wav"),
    /**
     * The cue to be played when AudioBug starts.
     */
    STARTUP("Sounds/Startup.wav"),
    /**
     * The cue to be played when the caret enters a line with a warning.
     */
    WARNING("Sounds/Warning.wav");

    /**
     * The path to the specified audio file in the JAR archive.
     */
    private final String resourcePath;

    /**
     * Constructor.
     *
     * @param resourcePath the path to an audio file resource
     */
    Cue(String resourcePath) {
        this.resourcePath = resourcePath;

    }

    /**
     * @return the resource path string
     */
    public String getResourcePath() {
        return this.resourcePath;
    }
}
