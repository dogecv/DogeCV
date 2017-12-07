

<div align="center">
    <img src="https://i.imgur.com/PK5wYK5.png" width="100%"/>
    <br></br>
  <p>
    <a href="https://discord.gg/qCRpgEY"><img src="https://discordapp.com/api/guilds/345404637374971907/embed.png" alt="Discord server" /></a>
  </p>
  <b>Created by Alex Carter of Disnode Robotics</b>
    <br/>
     <i>Version 0.3 Last Updated 12/6/17</i>

</div>

# DogeCV
A easy to use computer vision library used for FTC Games to detect game objects. Based on Ender CV and OpenCV. 


# DISCLAIMER
### THIS REPO IS STILL UNDER HEAVY DEVELOPMENT. I WILL BE ADDING FURTHER DOCUMENTATION, BUG FIXES AND NEW DETECTORS SOON. MANY OF THE 
However, although many of the detectors are currently pretty basic, I am putting alot of time in effort into this lib, and open sourced it to let the community work or learn from my mistakes. This is the exact code my team will be running so I do have a decent motivation to work on it ;)

## Known Issues
- Glyph Slow Performance

## Planned Features / TODO
- Jewel Position Detector
- Glyph Color Reading
- Score based Cryptobox detection to increase accuracy.
- Previous frame's results to increase accuracy in detectors
- Motion Tracking for Cryptobox
- HSV Calibrating


## Install (Credit to EnderCV)
1. Download this repo, either by cloning from Git or using the zip download. 
2. Open up your FTC Application
3. Navigate to **File** -> **New** -> **Import Module** from the title bar.
4. When the a dialog comes up, asking for the module source directory, navigate to this repo and select the **openCVLibrary320** folder, and then hit **Finish**
5. Repeat steps 3 and 4 except instead of selecting the **openCVLibrary320** folder, select the **DogeCV** folder instead.
6. In the left hand side project explorer in Android Studio, right-click **TeamCode**, and click on **Open Module Settings**.
7. A **Project Struture** dialog should come up. Click the **Dependencies** tab.
8. Click the green plus sign on the right hand side, then **Module dependency**, and then **:openCVLibrary320**, then press OK.
9. Repeat step 8, except substitute **:openCVLibrary320** with **:dogecv**
10. Click **OK** to exit the **Project Structure** dialog.
## Detectors

### Glyph Detector (Working, last Updated: 12/6/17)
This is a detector that uses a mix of filters and canny edge detection that is fed into FindContours. Then each result is scored based on Ratio, Area,
Distance from Bottom-Center of the screen, and soon color. The top scoring result is returned. The value that will be returned inside DogeCV will be a distance
from Center Screen on the X Axis. This can be fed into the bot to tell it which direction to turn.
#### Detector Classes
 - `GlyphDetector` - Cryptobox Detector
#### Parameters
- `downScaleFactor` - double representing how much to downscale each frame. (Lower = Faster) 
- `speed` - Speed setting for the detector. (how fast vs how good)
- `rotateMat` - Rotate the image when processing (wont be visible on preview, change this if you see detections working horizontally) [Usually: Landscape = false, Portrait = true]
- `minScore` - The minimum score for results (threshold)
- `debugDrawStats` - Draw Scores for each result
- `debugDrawRects` - Draw All Found Rectangles

#### Returned Data
Currently This Detector Returns the Following:
- `getChosenGlyphPosition()` - The Position of the Choosen Glyph on the screen (Point)
- `getChosenGlyphOffset()` - The Distance of the chosen glyph from the center of the screen
- `isFoundRect()` - Is there a glyph found?

### Cryptobox Detector (Working, Last Updated: 12/6/17)
This detector finds the position of each column inside the cryptobox. It currently used HSV values to do this so color and lighting will effect it. Im looking
to other ways of doing this. 

Im currently developing a new version of this detector as it is basic and prone to failure, however I decided to release this as it's better the nothing. 

#### Detector Classes
 - `CryptoboxDetector` - Cryptobox Detector

#### Parameters
- `downScaleFactor` - double representing how much to downscale each frame.
- `detectionMode` - Mode used to detect, `HSV_BLUE` and `HSV_RED` are currently only implemented modes, each representing which color you what to detect.
- `speed` - Speed setting for the detector. (how fast vs how good)
- `rotateMat` - Rotate the image when processing (wont be visible on preview, change this if you see detections working horizontally) [Usually: Landscape = false, Portrait = true]
#### Returned Data
Currently This Detector Returns the Following:
- `isCryptoBoxDetected()` - Is the full box detected?
- `isColumnDetected()` - Is at least one column detected?
- `getCryptoBoxLeftPosition()` - Get the left column position (int on x-axis)
- `getCryptoBoxCenterPosition()` - Get the center column position (int on x-axis)
- `getCryptoBoxRightPosition()` - Get the right column position (int on x-axis)
- `getCryptoBoxPositions()` - Array on Ints that represent columns found, in order from left to right

### Jewel Detector (In Development v0)
This detector finds the orientations of the two Jewels, returning which one is left or right. This is HSV based so lighting and color will effect this detector.


## Contact
If you have any suggestions or questions feel free to contact me at:    
**VictoryForPhil@gmail.com**
or 
**VictoryForPhil#4759** on Discord

You can also usually spot me on the FTC Discord.
