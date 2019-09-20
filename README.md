

<div align="center">
    <img src="https://i.imgur.com/umLIEix.jpg" width="100%"/>
    <br></br>
  <p>
    <a href="https://discord.gg/colton"><img src="https://discordapp.com/api/guilds/345404637374971907/embed.png" alt="Discord server" /></a>
  </p>
  <b>Developed by Levi Gershon of Newton's Law and Alex Carter of Disnode Robotics</b>
    <br/>
     <i>Version 2019.1 | Updated 02/12/2019 </i>

</div>

# DogeCV
An easy to use computer vision library used for FTC Games to detect game objects. Based on Ender CV and OpenCV. 

## Project Status:
**MOSTLY COMPETITION READY.**

# DISCLAIMER
### THIS REPO IS STILL UNDER CONTINUOUS DEVELOPMENT. WE WILL BE ADDING FURTHER DOCUMENTATION, BUG FIXES, AND NEW FEATURES

## Videos
Wizards.exe have been amazing with their DogeCV coverage, so please check them out and give your support, the videos are easy to understand and well made, great for peopling who want to learn DogeCV. 

 - [Wizards.exe Tutorial](https://www.youtube.com/playlist?list=PLICNg-rquuraBSqMOeW_hqf9O-Cct1jJw)


## Active Development Team
- Aditya Mangalampalli FTC 9614 Hyperion @Λlpheron#2162
- Alex Carter FTC 7195 Mechanical Memes @VictoryForPhil#0001
- Arnav Komaragiri FTC 8719 Quantum Leap @-----#9037
- Ben Loan FTC 3795 JagWired @Ben6501#0094
- Daniel Goz FTC 7026 JDroids @dansman805#5805
- Frank Portman FTC 8581 Aedificatores @Something Disposable#9622

## Active Contributors
- Adhit Siripurapu FTC 9614 Hyperion (Testing) @Prickles#0203
- Ishaan Oberoi FTC 9794 Wizards.exe (Publicity) @RollerCoaster45#0637
- Nathaniel Lesser FTC 12897 Newton's Law of Mass' (Logos) 
- Sarthak Bhatnagar FTC 9794 Wizards.exe (Odometry & Tutorials) @s.bhatnag#0906

## Doge Alumni
- Levi Gershon FTC 12897 Newton's Law of Mass @LegoF4#2372

## Other Past Contributors
- Robert Iridon and Prodaniuc Pavel FTC 15994 CSH (Webcam testing)
- Nathaniel Lesser FTC 12897 Newton's Law of Mass' (Banner art)
- Karter FTC 5975 Cybots (Brainstorming for Jewel Detector)
- Derek FTC 5484 Enderbots (EnderCV Classes)
- Owen Gonzalez (Testing)
- Kwon Paradigm Break (Testing)

## Known Issues
(These issues are referring to the 2018-2019 FTC Year Detectors, and not the Relic Recovery ones as those are no longer supported)
- All detectors have a minor memory leak. Simply don't leave them running too long and you'll be fine.
- General Instability
- GenericDetector not ported
- RelicRecovery detectors not ported

## Planned Features / TODO
- Expand Wiki
- Add in distance scoring between samples to increase accuracy
- General Code Clean up
- Basic angle/positioning of elements
- Port Old detectors to new systems
- Better Safety checks
- New Frame Input System
- ML detectors


## Install (Credit to EnderCV)
1. Download this repo, either by cloning from Git or using the zip download. 
2. Pull up Android Studio, with the FTC application SDK open
3. Navigate to **File** -> **New** -> **Import Module** from the title bar.
4. When the a dialog comes up, asking for the module source directory, navigate to this repo and select the **openCVLibrary3** folder, and then hit **Finish**
5. Repeat steps 3 and 4 except instead of selecting the **openCVLibrary3** folder, select the **DogeCV** folder instead. If Android Studio fails to import modules because it sucks sometimes, open `settings.gradle` and add these two lines: 
```groovy
include ':openCVLibrary3'
include ':DogeCV'
```
and resync the project.    
6. Add the lines to TeamCode's `build.release.gradle`:
```groovy
implementation project(':openCVLibrary3')
implementation project(':DogeCV')
```
7. Press the `Sync Now` button that should appear in the top right

# Detectors Status
- **Gold Detector** - Implemented. Stable. Competition Ready.
- **Silver Detector** - Implemented. Stable. Competition Ready.
- **Gold Align Detector** - Implemented. Unstable (Memory Leak).
- **Sampling Detector** - Implemented. Stable. Not Competition Ready.
- **Hough Silver Detector** - Implemented. Unstable. Not Competition Ready.
- **MultiMineral Detector** - Not Yet Implemented
**See Wiki For More Info

## FAQ
- **If I use DogeCV can I still use Vuforia?**
    With the 2019 edition of DogeCV we have enabled the option to use Vuforia and DogeCV at the same time, all from one detector! See the VuMark Example class and the wiki for more information on how to do this.
    (Currently Unstable)
- **Can I still use classic OpenCV?**
    Yes! We want teams to use DogeCV to learn about vision and start to create their own vision systems. That's why OpenCV is open in all layers of DogeCV, and we keep the classic OpenCVPipepline introduced in the EnderCV library.
- **Can I use a webcam?**
    Once more, yes! See the WebcamExample OpMode and the wiki for an explanation of how to this.

## Changelogs
**2019.1**:
 - Complete re-work of the `Dogeforia` system. It is now integrated within the `OpenCVPipeline` class, and is much simpler.
 - Implemented image cropping, see `CroppingExample`
 - Fixed miss-rotation in Vuforia display. The image should now be correctly oriented on the RC display
 - Added `VuMarkExample` OpMode to demonstrate the basics of the new system
 - Added `WebcamGoldExample` OpMode to demonstrate how to use a webcam with a detector under the new system
 - Added `GoldAndVuMarks
 - Added `CroppingExample` OpMode, intended to ease image cropping and to showcase functionality
 - Added `GoldExample` OpMode
 - Added method `getYPosition()` to `GoldAlignDetector`
 - Added `VuMarkDetector` to provide a ready-made detector for the VuMarks within DogeCV. I will have it highlight VuMarks at a later date
 - Added enum `VuMark` to `DogeCV` to make dealing with VuMarks easier in code
 - Added enum 'CameraMode` to `DogeCV` to make setting the camera between `FRONT`, `BACK`, and `WEBCAM` easier
 - Added wiki entries for VuMarks and Webcams
 - Reworked code comments
 - Removed camera index as a constructor argument in `OpenCVPipeline`. This has been replaced by `DogeCV.CameraMode`
 - Updated page banner - thanks Nathan!
 - Updated this `README`

**2018.2.1 HOTFIX**:
 - Removed `AsyncFilterRunner` import from Sampling Detector #12   
 - Fixed `Dogeforia` crash on stop #10        

**2018.2**:
 - Added `HoughSilverDetector`: intended for slighter slower but more precise silver mineral classification. Works nicely.
 - Added JavaDocs and in-depths comments on most detectors, scorers, and filters
 - Changed `DogeCVScorer` input from MatOfPoint to Mat in order to facilitate more diverse scoring methods
 - Detectors now have a seperate `displayMat` as opposed to a `workingMat`; one is for detection work, the other is for displaying the results
 - Added `SilverExample` OpMode for demonstrating silver detector use
 - Added `HoughSilverExample` OpMode for demonstrating Hough transform-based silver detector use
 - Gold Align Detector now has `setAlignSettings(int offset, int width)` function to set parameters
 - Updated `SamplingOrderDetector` to choose top two silver minerals instead of all
 - Ported `GenericDetector` to using `DogeCVDetector`
 - Ported `BlankDetector` to using `DogeCVDetector`
 - Added `updateSettings` to `LeviColorFilter`
 - Corrected typos within this `README`
 - Added an experimental `WHITE` option to `LeviColorFilter`
 - Cleaned up detectors and filters

**2018.1**:
 - Added `HSVRangeFilter` that uses classic `lower` and `upper` HSV ranges
 - `HSVColorFilter` now divides the range by 2 on each side of the perfect color value. (Now range acts as expected) (Issue #9)
 - Added `SilverDetector` (Same as Gold but for silver)
 - Moved `SamplingOrderDetector` to use `HSVRangeFilter` for silver
 - Tuned Sampling (Still not competition ready)
 - Cleaned up `Dogeforia` handling per suggestions of "@BillTheCat123 | Mentor | 3763" on FTC Discord
 - Fixed Gradle import issues of `:FTCRobotController` with `Dogeforia` class. (Issue #8)
 - Cleaned up Gradle Build files to lessen import errors
 - Added common Gradle Build errors to wiki
 - `DogeCVDetector` now handles printing the detector setting to the screen

**2018.0**:
 - New Versioning System
 - New Scoring API
 - New DogeCVDetector Class
 - Vuforia Support
 - Gold Align, Mineral Order, and Gold Detectors
 - General Code Cleanup
 - Cleaner Params
 - Moved Downscaling to DogeCVDetector Class
 - Updated to EnderCV 2.0 (Modfied)
 
**1.1.1 HOTFIX**:
 - Fixed Jewel Detector Blue Filter
 

**1.1**:
- New Color Filter API   
- New Generic Detector     
- Fixed Jewel Debug Scores    
- Fixed Imports for DogeLogger inside Cryptobox Detector   
- Ported all detectors to Color Filter API     
- Added Yellow to LeviColorFilter
- Added HSV color filter
- New Relic/Generic Example
 
**1.0**:
 - New Cryptobox Detector
 - YouTube Tutorials
 - Per-Detector Documentation
 - Wiki Start 
 - Added `perfectRatio` tuning for Jewels
 - Optimization
 - Removed Multiple Mat returning

**0.5**:
 - Fixed rotated preview on portrait mode.
 - Detectors return an array of images. You can cycle through them by tapping on the preview screen

## Contact
Please feel free to message us on Discord with the usernames that we have listed above.
You can also usually spot us on the FTC Discord and feel free to ask us if you have any questions.
