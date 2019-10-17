package com.disnodeteam.dogecv.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class GrayscaleFilter extends DogeCVColorFilter {
    private Scalar lower;
    private Scalar upper;

    public GrayscaleFilter(int lower, int upper) {
        this.lower = new Scalar(lower);
        this.upper = new Scalar(upper);
    }

    public void process(Mat input, Mat mask) {
        // Convert the input to grayscale
        Imgproc.cvtColor(input,input,Imgproc.COLOR_RGB2GRAY);

        // Blur it
        Imgproc.GaussianBlur(input,input,new Size(5,5),0);
        // Run in range check
        Core.inRange(input, lower, upper, mask);
        input.release();
    }

    public void setValues(int lower, int upper) {
        this.lower = new Scalar(lower);
        this.upper = new Scalar(upper);
    }
}
