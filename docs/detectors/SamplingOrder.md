![]()
**STATUS:** _NOT COMPETETION READY (Needs Accuracy Improvements)_   
**LAST UPDATED:** _9/22/2018 DogeCV 2018.0_

# About
This sampling order detector, when it sees all 3 sampling minerals, will return the position of the gold mineral. This allows you to knock it off succesfully.

### Todo
- Distance between minerals should affect scoring
- Guessing with only two minerals in sight
- Better silver detection
- Implement DogeCV performance settings
- Pick top 2 Scoring Elements instead of any two
### Known Issues
- Brightly lit floor can cause issues
- Needs all 3 minerals in sight
### Credits

# Details
The sampling order detector uses a Yellow `LeviColorFilter` to locate 1 gold mineral and an `HSVColorFilter` to locate all white minerals, then compares their X coordinates to find the location of the gold. Gold position is either `CENTER`, `LEFT`, or `RIGHT` (or `UNKNOWN` if we do not know the current location)

# Usage
This is made for those writing autonomous programs in which the location of the gold mineral needs to be known, and whose camera placement allows the robot to see all 3 minerals.

## Scorers (See ScoringAPI for tuning each.)
- `ratioScorer` - a `RatioScorer` to find perfect squares.
- `maxAreaScorer` - a `MaxAreaScorer` to find perfect large objects
- `perfectAreaScorer` - a `PerfectAreaScorer` to find tuned size objects.
**NOTE:** In this detector, setting `areaScoringMethod` will determine which area scorer to use. 

## Parameters
- `areaScoringMethod` - (`DogeCV.AreaScoringMethod `) - an enum to determine which area scorer to use. (`MAX_AREA` or `PERFECT_AREA`)
- `ratioScorer` - **SEE ABOVE**
- `maxAreaScorer` - **SEE ABOVE**
- `perfectAreaScorer` -  **SEE ABOVE**
- `yellowFilter` - (`DogeCVColorFilter `) - Filter used to find gold (Defaults to `LeviColorFilter(LeviColorFilter.ColorPreset.YELLOW,70)`)
- `whiteFilter` - (`DogeCVColorFilter `) - Filter used to find gold (Defaults to `new HSVColorFilter(new Scalar(40,25,200), new Scalar(40,40,50))`)

## Returned Data
Currently this detector returns the following:
- `isFound()` - Is the mineral row detected?
- `getLastOrder()` - Get the last known mineral order (`UNKOWN`, `LEFT`, `RIGHT` or `CENTER`)
- `getCurrentOrder()` -  Get the current mineral order (`UNKOWN`, `LEFT`, `RIGHT` or `CENTER`)

# Changelogs
- **2018.0**
  - First Public Release.
