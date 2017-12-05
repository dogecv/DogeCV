

<div align="center">
  <p>
    <a href="https://discord.gg/qCRpgEY"><img src="https://discordapp.com/api/guilds/345404637374971907/embed.png" alt="Discord server" /></a>
  </p>

</div>

# DogeCV
A easy to use computer vision library used for FTC Games to detect game objects. Based on Ender CV and OpenCV. 
![Happy Customer](https://media.discordapp.net/attachments/373628631970217984/384547591154827266/image.png)


# DISCLAIMER
### THIS REPO IS STILL UNDER HEAVY DEVELOPMENT. I WILL BE ADDING FURTHER DOCUMENTATION, BUG FIXES AND NEW DETECTORS SOON

## Known Issues
- Only Works in Landscape
- Diffrent Camera Specs can cause a crash

## Planned Features / TODO
- Jewel Position Detector
- Glyph Color Reading
- Score based Cryptobox detection to increase accuracy.
- Previous frame's results to increase accuracy in detectors
- Motion Tracking for Cryptobox
- HSV Calibrating
- Fix Naming Conventions
- Landscape Mode
- Adjustible Resolution


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

### Glyph Detector (Detecting, last Updated: 12/1/17)
This is a detector that uses a mix of filters and canny edge detection that is fed into FindContours. Then each result is scored based on Ratio, Area,
Distance from Bottom-Center of the screen, and soon color. The top scoring result is returned. The value that will be returned inside DogeCV will be a distance
from Center Screen on the X Axis. This can be fed into the bot to tell it which direction to turn.

#### Parameters
- `MinScore` - The mininum score to return the values. Think of this as a threshold for results.

#### Returned Data
Currently This Detector Returns the Following:
- `ChosenGlyphPos` - The X Position of the Choosen Glyph on the screen
- `ChosenGlyphOffset` - The Distance of the chosen glyph from the center of the screen
- `FoundRect` - Is there a glyph found?

### Cryptobox Detector (Working, Last Updated: 12/4/17)
This detector finds the position of each column inside the cryptobox. It currently used HSV values to do this so color and lighting will effect it. Im looking
to other ways of doing this. 

#### Detector Classes
 - `CryptoboxDetectorRed` - Red Cryptobox Detector
 - `CryptoboxDetectorBlue` - Blue Cryptobox Detector

#### Parameters
*none*

#### Returned Data
Currently This Detector Returns the Following:
- `isCryptoBoxDetected()` - Is the full box detected?
- `isColumnDetected()` - Is at least one column detected?
- `getCryptoBoxLeftPosition()` - Get the left column position (int on x-axis)
- `getCryptoBoxCenterPosition()` - Get the center column position (int on x-axis)
- `getCryptoBoxRightPosition()` - Get the right column position (int on x-axis)
- `getCryptoBoxPositions()` - Array on Ints that represent columns found, in order from left to right

### Jewel Detector (In Development)
This detector finds the orientations of the two Jewels, returning which one is left or right. This is HSV based so lighting and color will effect this detector.


## Contact
If you have any suggestions or questions feel free to contact me at:    
**VictoryForPhil@gmail.com**
or 
**VictoryForPhil#4759** on Discord

You can also usually spot me on the FTC Discord.
