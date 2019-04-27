package com.disnodeteam.dogecv.scoring;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;

import java.util.List;

/**
 * Created by Victo on 9/10/2018.
 *
 * Gives a score for a given Mat for certain criteria defined in the class
 */

public abstract class DogeCVScorer {
    /**
     * Gives the score for a given Mat
     * @param input the Mat to check the score of
     * @return the score for the input Mat
     */
    public abstract double calculateScore(Mat input);
}
