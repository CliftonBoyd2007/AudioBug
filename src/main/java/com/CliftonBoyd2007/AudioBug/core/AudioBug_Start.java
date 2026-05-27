package com.CliftonBoyd2007.AudioBug.core;




import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.project.Project;

import java.io.File;

import com.CliftonBoyd2007.AudioBug.audioutils.CuePlayer;
import org.jetbrains.annotations.NotNull;

public class AudioBug_Start implements StartupActivity {

    /**
     * Initializes file resources and instantiates {@link CuePlayer} object for cue playback.
     *
     * @param project The currently open project.
     */
    @Override
    public void runActivity(@NotNull Project project) {
        ClassLoader cl = this.getClass().getClassLoader();
        File cue_Breakpoint = new File(String.valueOf(cl.getResource("Sounds/Breakpoint.wav")));
        File cue_Error = new File(String.valueOf(cl.getResource("Sounds/Error.wav")));
        File cue_Warning = new File(String.valueOf(cl.getResource("Sounds/Warning.wav")));
        CuePlayer cuePlayer = new CuePlayer(cue_Breakpoint, cue_Error, cue_Warning);

    }

}