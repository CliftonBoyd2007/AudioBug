# AudioBug

AudioBug is a plugin for JetBrains IDEs that provides non-visual editor feedback to someone using a screen reader.
Inspired by the accessible feedback in Visual Studio and Visual Studio Code, it brings a similar experience to JetBrains
IDEs.


## Why AudioBug? 

JetBrains IDEs are incredibly powerful and popular tools used by many software engineers around the world. One of their biggest advantages is their code intelligence features. However, these features heavily rely on immediate visual feedback within the editor, making it difficult to use those features if the user is blind or visually impaired. A sighted user can quickly glance at the squiggles or lightbulbs without leaving the editor. On the other hand, someone who uses a screen reader would have to toggle back-and-forth between the editor and problems view to get that same information. AudioBug bridges this gap by providing spoken and auditory feedback to convey the presence of errors and warnings where the cursor is located, allowing for the same level of immediate feedback while navigating and editing code. 


## Requirements

- IntelliJ Platform 2025.3 or later
- Active screen reader
- Gradle 9.5.0 or later (if building from source)

### Tested With

- NVDA 2025.3.2 up to and including 2026.1.1
- JAWS 2024
- VoiceOver on macOS 26.6 (25G72)
- Rider, IntelliJ IDEA, CLion (2025.3 up to and including 2026.2)

## Key Features

- Automatic Diagnostic Announcements - Sound cues and spoken feedback for errors and warnings associated with the
  current line.
- Read Error/Warning Descriptions - When alt/option+R is pressed, read the description of the error or warning
  associated
  with the current line. If multiple errors/warnings are present, repeatedly press the keystroke to cycle through them.

## Installation

- From the [releases section](https://github.com/CliftonBoyd2007/AudioBug/releases), download the latest release. The
  file name is of the form AudioBug-[version number].jar.
- Once downloaded, verify the file's SHA-256 hash.

    - On Windows (from Command Prompt or PowerShell):

      ```cmd
      certutil -hashfile path\to\AudioBug-[version].jar
      ```

      Where "path\to\AudioBug-[version].jar" is the path to the AudioBug JAR archive on your computer that you
      downloaded.

      The output of the above command will be of the form:

      ```cmd
      SHA256 hash of
      path\to\AudioBug-[version].jar:
  
  
      930e34796bbde0525cdd281145dee8ebd132a408fec5a98141fdb25119228c3d
      CertUtil: -hashfile command completed successfully.
      ```

    - On macOS (from Terminal):

      ```zsh
      sha256sum /path/to/AudioBug-[version].jar
      ```

      Where "/path/to/AudioBug-[version].jar" is the path to the downloaded AudioBug JAR archive on your computer.

      The output will simply be the computed hash. Compare it to the one displayed on the GitHub releases page for the
      version you downloaded.
- Once the file hash has been verified, refer
  to [the instructions given by JetBrains here](https://www.jetbrains.com/help/idea/managing-plugins.html#install_plugin_from_disk)
  under the heading "Install Plugin From Disk" for instructions on installing the plugin from disk.
- Once installed, restart the IDE.

### IMPORTANT

AudioBug will not announce that it is installed. To ensure that it is running after you restart the IDE, press
alt/option+R in an open editor. The active screen
reader should announce either "No errors or warnings" or, if there is an error or warning associated with the current
line, its description (e.g., "';' expected").

## Important Notes

- This software is stable in my testing, however it is still in early development. I have not tested it in all JetBrains
  IDEs. Please set your expectations
  accordingly.
- VoiceOver users - VoiceOver suppresses automatic diagnostic announcements from AudioBug. On-demand
  announcements are still spoken by VoiceOver and shown on a connected Braille display.
- JAWS and NVDA users - AudioBug announcements do not appear as flash messages on a Braille display.