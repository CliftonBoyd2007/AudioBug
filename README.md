# AudioBug

AudioBug is a plugin for JetBrains IDEs that provides non-visual editor feedback to someone using a screen reader.
Inspired by some of the accessibility features of Visual Studio and Visual Studio Code, AudioBug brings a similar
experience to
JetBrains IDEs by utilizing audio cues and spoken feedback to convey information rather than visuals in order to make
software
development for the blind and visually impaired more accessible in more environments.

## Why AudioBug?

JetBrains IDEs are incredibly powerful and popular tools used by many software engineers around the world. One of their
biggest advantages is their code intelligence features. However, these features heavily rely on immediate visual
feedback within the editor, making it difficult to use those features if the user is blind or visually impaired. A
sighted user can quickly glance at visual landmarks, such as squiggles or light bulbs, without leaving the editor. By contrast, someone
who uses a screen reader has to toggle between the editor and problems view to get that same
information. AudioBug bridges this gap by providing spoken and auditory feedback to convey the presence of errors and
warnings where the cursor is located, allowing for the same level of immediate feedback while navigating and editing
code.

## Key Features

- Automatic Diagnostic Announcements - Sound cues and spoken feedback for errors and warnings associated with the
  current line.
- Read Error/Warning Descriptions - When alt/option+R is pressed, read the description of the error or warning
  associated
  with the current line. If multiple errors/warnings are present, repeatedly press the keystroke to cycle through them.

## Requirements

- IntelliJ Platform 2025.3 or later
- Active screen reader
- Gradle 9.5.0 or later (if building from source)

### Tested With

- NVDA 2025.3.2 up to and including 2026.1.1
- JAWS 2024
- VoiceOver on macOS 26.6 (25G72)
- Rider, IntelliJ IDEA, CLion (2025.3 up to and including 2026.2)

## Installation

- From the [releases section](https://github.com/CliftonBoyd2007/AudioBug/releases), download the latest release. The
  file name is of the form AudioBug-[version number].jar.
- Once downloaded, verify the file's SHA-256 hash.

    - On Windows (from Command Prompt or PowerShell):

      ```cmd
      certutil -hashfile path\to\AudioBug-[version].jar SHA256
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

## Important Notes

- In AudioBug 0.2.2 and later, a sound will be played upon startup. However, this happens long before the editor is
  open. As such, when the editor is open and ready, it will announce "AudioBug is ready." Please be aware that this
  announcement may be interrupted by other screen reader activity. To check that it is running, press alt+R (Windows) or option+R (macOS). It will either announce "No errors or warnings," or--if the cursor lands on a line with one--its description (e.g., "Cannot resolve symbol 'undeclaredVariable'").
- This software is stable in the environments that I have tested it in. I have not tested it in every JetBrains
  IDE or configuration. Please set your expectations
  accordingly.
- VoiceOver users - VoiceOver suppresses automatic diagnostic announcements from AudioBug. On-demand
  announcements are still spoken by VoiceOver and shown on a connected Braille display.
- JAWS and NVDA users - AudioBug announcements do not appear as flash messages on a connected Braille display.
