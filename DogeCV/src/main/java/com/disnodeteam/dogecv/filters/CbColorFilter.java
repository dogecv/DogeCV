package com.disnodeteam.dogecv.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class CbColorFilter extends DogeCVColorFilter {
    Scalar lower;
    Scalar upper;

    public CbColorFilter(int lower, int upper) {
        this.lower = new Scalar(lower);
        this.upper = new Scalar(upper);
    }

    @Override
    public void process(Mat input, Mat mask) {
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YCrCb);

        // Blur it
        Imgproc.GaussianBlur(input,input,new Size(5,5),0);
        // Run in range check
        Core.inRange(input, lower, upper, mask);
        input.release();
    }
}
