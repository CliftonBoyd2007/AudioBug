
package com.CliftonBoyd2007.AudioBug.audioutils;
public enum Cue {
    BREAKPOINT("Sounds/Breakpoint.wav"),
    ERROR("Sounds/Error.wav"),
    STARTUP("Sounds/Startup.wav"),
    WARNING("Sounds/Warning.wav");
    /**
     * The path to the audio files in the JAR archive.
     */
    private final String resourcePath;

    /**
     * Constructor for each enum element.
     *
     * @param resourcePath the path to each audio file
     */
    Cue(String resourcePath) {
        this.resourcePath = resourcePath;

    }

    /**
     *
     * @return the resource path string
     */
    public String getResourcePath() {
        return this.resourcePath;
    }
}
