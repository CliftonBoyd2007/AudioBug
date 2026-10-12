<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# AudioBug Changelog

## [Unreleased]
## [0.3.0-beta11] - 2026-10-12 

### Changed 

- Revert to PSI behavior from 0.3.0-beta9.

## [0.3.0-beta10] - 2026-10-11

### Changed 

- Read the node two levels up from the leaf node at the caret.

## [0.3.0-beta9] - 2026-10-11
### Changed 

- The parent of the PSI elemtn at the caret is now spoken instead of the element directly at the caret. 

## [0.3.0-beta8] - 2026-10-11 

- Change keyboard shortcut to invoke the action. The shortcut is now ctrl Alt Shift Space. 

## [0.3.0-beta7] - 2026-10-11

### Added 

- Added ability to read the PSI element at the caret with the active screen reader. This is very much not stable. 


## [0.3.0-beta4] - 2026-10-03

- Begin migrating caret-based functionality from LineHighlightLocator to CaretStateService.

### Added 

- Added line offset storage in CaretStateService. This functionality wil eventually be removed from LineHighlightLocator.
 

## [0.3.0-beta3] - 2026-10-02

### Changed 

- Changed EditorStateService so that it stores the line numbers directly. 
- Move the logic for updating it so that it happens when the caret moves lines. 

### Fixed 

- Fixed line numbers that were off by one.
- Fixed erroneous and nonsensicle line numbers (e.g., line 0).
- Fixed bug that prevented line numbers from being updated reliably.

### Removed 

- Removed startup sound and announcement.

## [0.3.0-beta2] - 2026-10-02

### Changed

- Change logic to determine current line to use visual position instead of logical position.

## [0.3.0-beta1] - 2026-10-02

### Added

- Added ability to have AudioBug announce the line number of the line on which the caret is located by pressing
  Ctrl+Alt+Shift+G.

## [0.2.16] - 2026-10-02

- No user-facing improvements.
- Add functionality to remove the CaretWatcher when an editor is released.

## [0.2.15] - 2026-09-22

- No user-facing improvements.
- Code improvements.

## [0.2.14] - 2026-09-20

- No user-facing improvements.
- Code cleanup build.

## [0.2.13] - 2026-09-20

- No user-facing improvements.
- Code improvements.

## [0.2.12] - 2026-09-20

- No user-facing improvements.
- Code improvements.

## [0.2.11] - 2026-09-20

- No user-facing improvements
- Code restructuring for better organization and maintainability.

## [0.2.10] - 2026-09-20

- No user-facing changes.
- Internal code structure change for organization.

## [0.2.9] - 2026-09-05

### Changed

- Changed keyboard shortcut for reading highlight descriptions aloud. The new shortcut is option/alt+P.

## [0.2.8] - 2026-08-19

- No user-facing improvements
- Code cleanup build

## [0.2.6] - 2026-08-19

- Code cleanup build

### Fixed

- Fixed bug that would cause NullPointerExceptions when querying for highlights.

## [0.2.5] - 2026-08-18

- No user-facing improvements
- Code cleanup build

## [0.2.4] - 2026-08-17

### Changed

- Changed audio playback subsystem resource management for easier future expansion.

## [0.2.3] - 2026-08-17

### Changed

- Changed where resource paths for audio cues are stored in preparation for CuePlayer rewrite.

## [0.2.2] - 2026-08-07

### Added

- Added startup announcement.

## [0.2.1] - 2026-08-07

### Added

- Added audio cue for when AudioBug initially starts up.

## [0.2.0] - 2026-08-05

### Added

- Added error/warning cycling for when multiple errors or warnings exist on the same line.

### Changed

- Changed default behavior for automatic announcements to no longer interrupt current screen reader speech.

## [0.1.2] - 2026-07-20

### Added

- Added sound cues to accompany error/warning announcements.

## [0.1.1] - 2026-07-20

### Fixed

- Fixed incorrect "error" announcement for when warnings are encountered.

## [0.1.0] - 2026-07-20

### Added

- Added automatic error/warning announcements for when an error or warning is encountered.

## [0.0.2] - 2026-07-18

### Added

- Added audio playback subsystem for error and warning cues.

## [0.0.1] - 2026-07-09

### Added

- Added editor cursor tracking and screen reader speech output subsystem.