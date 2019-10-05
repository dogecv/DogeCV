package com.disnodeteam.dogecv.filters;

import org.opencv.core.Mat;

/**
 * Created by Victo on 1/1/2018.
 *
 * A color filter interface
 */

public abstract class DogeCVColorFilter {
    /**
     * Takes an input Mat and then puts the filtered result on the mask Mat
     *
     * @param input the Mat to filter
     * @param mask the Mat to put things that passs the filter
     */
    public abstract void process(Mat input, Mat mask);

}
