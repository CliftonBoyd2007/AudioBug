# AudioBug

Empowering blind and visually impaired developers in JetBrains integrated development environments.

## About the project

JetBrains IDEs provide a lot of information, such as

- Error/warning squiggles,
- Gutter icons,
- and inlay hint popovers.

However, it is very difficult to access this information without visual context. AudioBug aims to bridge the gap by
presenting errors and warnings through clear, concise audio feedback.

## Features

### Automatic Diagnostic Information

AudioBug monitors the location of the caret and produces feedback when the cursor enters a line with an error or
warning.

When an error or warning is encountered, the following happens:

- A sound cue is played
- AudioBug announces "Error" or "Warning."

These announcements are intentionally very terse to prevent prolonged interuptions.

### Read Error/Warning Description

At the request of the end-user, AudioBug can read the exact message of the diagnostic; for example, "';'
expected," or "Cannot find symbol."

### Diagnostic Cycling

When multiple error and/or warnings exist on the same line, AudioBug allows the user to cycle through them. For example, if there are three errors on one line, the user can press Option+R (Mac) or alt+R (Windows), to read through each one sequentially. When the user reaches the last diagnostic on that line, AudioBug will automatically read the first one if the user presses the keystroke again. 


## Prerequisites 

- IntelliJ Platform 2025.3 or later
- Active Screen Reader (NVDA, JAWS, VoiceOver)
- Gradle 9.5.0 or later 

Note: AudioBug is primarily tested in IntelliJ Idea and Rider. Other IDEs based on supported platform versions may work, but functionality has not been verified. 


## Building AudioBug 

To build AudioBug, follow these steps:

- Clone this repo
```bash
git clone https://github.com/CliftonBoyd2007/AudioBug 
```

- Navigate to the directory containing the AudioBug repo. 
- If you are running macOS, before building AudioBug, run 

```bash
chmod +x ./gradlew
```

And then 
```bash
./gradlew build 
```
- If you are running Windows, run 

```cmd 
.\gradlew.bat build
```


The build output will be located at ./build/libs/Audiobug-[version].jar.

- For instructions on installing the plugin from disk, refer to [the instructions given here by JetBrains](https://www.jetbrains.com/help/idea/managing-plugins.html#install_plugin_from_disk) under the heading "Installing Plugins from Disk."

the instructions apply to other JetBrains IDEs. 