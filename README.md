# DogeCV
[![](https://jitpack.io/v/dogecv/dogecv.svg)](https://jitpack.io/#dogecv/dogecv)

An easy to use computer vision library used for FTC Games to detect game objects. 
Based on [EasyOpenCV](https://github.com/OpenFTC/EasyOpenCV) and OpenCV. 

## Project Status:
**In Development**

# DISCLAIMER
### THIS REPO IS STILL UNDER CONTINUOUS DEVELOPMENT. WE WILL BE ADDING FURTHER DOCUMENTATION, BUG FIXES, AND NEW FEATURES
### To run this library, you need an SDK version of at least 5.1
### Unfortunately, this library will not work on the ZTE phones, as OpenCV does not work on KitKat.
### Unfortunately, the video tutorials, are outdated and will be updated soon. Please read the installation directions and be check out the examples inside the code. Please contact any of the developed if you need help!

## Videos (OUTDATED!!!)
Wizards.exe have been amazing with their DogeCV coverage, so please check them out and give your support, the videos are easy to understand and well made, great for people who want to learn DogeCV for past versions. Please do keep in mind that these tutorials are for older versions of DogeCV and will not work for the newest version.  

 - [Wizards.exe Tutorial](https://www.youtube.com/playlist?list=PLICNg-rquuraBSqMOeW_hqf9O-Cct1jJw)

# Detectors Status
- **Stone Detector** - Implemented. Needs improvement.
- **Skystone Detector** - Implemented. Not Reliable, Under rewrite.

## Active Development Team
- Aditya Mangalampalli FTC 9614 Hyperion @Alpheron#2162
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
- [OpenFTC](https://github.com/OpenFTC) Team for [EasyOpenCV](https://github.com/OpenFTC/EasyOpenCV) which this library is based on

## Doge Alumni
- Levi Gershon FTC 12897 Newton's Law of Mass @LegoF4#2372

## Other Past Contributors
- Robert Iridon and Prodaniuc Pavel FTC 15994 CSH (Webcam testing)
- Nathaniel Lesser FTC 12897 Newton's Law of Mass' (Banner art)
- Karter FTC 5975 Cybots (Brainstorming for Jewel Detector)
- Derek FTC 5484 Enderbots (EnderCV Classes)
- Owen Gonzalez (Testing)
- Kwon Paradigm Break (Testing)

## Install (Credit to EasyOpenCV)
1. Pull up Android Studio, with the FTC application SDK open
2. Go to the root `build.gradle`
3. To the repositories section, add the lines 
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' } // this line!
  }
}
```
3. Add the line `implementation 'com.github.dogecv:dogecv:2020.4-alpha'` to TeamCode's `build.release.gradle`, inside the dependencies block
7. Press the `Sync Now` button that should appear in the top right
8. Because EasyOpenCv depends on OpenCV-Repackaged, you will also need to copy [libOpenCvNative.so](https://github.com/OpenFTC/OpenCV-Repackaged/blob/master/doc/libOpenCvNative.so) from the `/doc` folder of that repo into the `FIRST` folder on the internal storage of the Robot Controller.


## Known Issues
(These issues are referring to the 2019-2020 FTC Year Detectors, and not the Relic Recovery or Rover Ruckus ones as those are no longer supported)
- Skystone detectors aren't accurate 
- Stone detectors aren't accurate

## Planned Features / TODO
- Expand Wiki
- Add in distance scoring between samples to increase accuracy
- General Code Clean up
- Basic angle/positioning of elements
- Port Old detectors to new systems
- Better Safety checks
- New Frame Input System
- ML detectors

## FAQ
- **Can I still use classic OpenCV?**
    Yes! We want teams to use DogeCV to learn about vision and start to create their own vision systems. That's why OpenCV is open in all layers of DogeCV, and we keep the classic OpenCvPipepline introduced in the EasyOpenCV library.
- **Can I use a webcam?**
    Once more, yes! See the DogeQuickStart and the wiki for an explanation of how to this.

## Changelogs
**2020.1-alpha**:
 - Changed to EasyOpenCV from EnderCV (thanks OpenFTC team!)
 - Added `StoneDetector`
 - Added `SkystoneDetector`
 - Removed legacy detectors (may be added back later)
 - Added `DigitalCamera` class for localization
 
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
