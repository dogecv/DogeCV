package com.disnodeteam.dogecv.detectors.roverrukus;

import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.opencv.core.Mat;

/**
 * Created by LeviG on 1/20/2019.
 *
 * An incomplete VuMark detector
 */

public class VuMarkDetector extends DogeCVDetector {

    /**
     * Returns the input frame
     * @param rgba the input frame
     * @return the input frame
     */
    @Override
    public Mat process(Mat rgba) {
        return rgba;
    }

    /**
     * Does nothing, should set the scorer
     */
    @Override
    public void useDefaults() {}
}
