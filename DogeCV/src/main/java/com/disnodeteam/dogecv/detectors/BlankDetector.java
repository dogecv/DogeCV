package com.disnodeteam.dogecv.detectors;

import com.disnodeteam.dogecv.OpenCVPipeline;
import com.disnodeteam.dogecv.filters.DogeCVColorFilter;
import com.disnodeteam.dogecv.filters.LeviColorFilter;

import org.opencv.core.Mat;

/**
 * Created by Victo on 12/17/2017.
 *
 * An empty detector
 */

public class BlankDetector extends DogeCVDetector {
    /**
     * Returns the input mat
     * @param input the mat to return
     * @return the input mat
     */
    @Override
    public Mat process(Mat input) {
        // Process frame
        return input;
    }

    /**
     * Does nothing; in a real detector it would add the scorers
     */
    @Override
    public void useDefaults() {
        // Add in your scorers here.
    }
}
