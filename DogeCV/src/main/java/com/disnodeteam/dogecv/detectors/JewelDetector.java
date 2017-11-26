package com.disnodeteam.dogecv.detectors;


import com.disnodeteam.dogecv.OpenCVPipeline;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 11/5/2017.
 */

public class JewelDetector extends OpenCVPipeline {

    public enum JewelOrder {
        RED_BLUE,
        BLUE_RED,
        UNKNOWN
    }


    private JewelOrder order = JewelOrder.UNKNOWN;

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {
        Mat hsv = new Mat();
        Imgproc.cvtColor(rgba, hsv, Imgproc.COLOR_RGB2HSV);

        Mat structure = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(40,100));
        Imgproc.morphologyEx(hsv,hsv,Imgproc.MORPH_OPEN, structure);
        double bluePos = GetBlue(hsv);


        return hsv;
    }

    public double GetBlue(Mat input){
        Scalar lower = new Scalar(80,55,40);
        Scalar upper = new Scalar(115,255,230);

        Mat mask = new Mat();
        Core.inRange(input,lower,upper,mask);

        Mat hierachy = new Mat();
        List<MatOfPoint> countours = new ArrayList<>();

        Imgproc.findContours(mask,countours,hierachy,Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE);


        return -1;
    }

    public JewelOrder getOrder() {
        return order;
    }
}
