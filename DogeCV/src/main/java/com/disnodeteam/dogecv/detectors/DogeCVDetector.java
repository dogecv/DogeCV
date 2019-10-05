package com.disnodeteam.dogecv.detectors;

import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.OpenCVPipeline;
import com.disnodeteam.dogecv.math.MathFTC;
import com.disnodeteam.dogecv.scoring.DogeCVScorer;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 9/10/2018.
 *
 * A base class for detectors
 */

public abstract class DogeCVDetector extends OpenCVPipeline{
    /**
     * Processes the input mat
     * @param input the mat to be processed
     * @return the processed mat
     */
    public abstract Mat process(Mat input);

    /**
     * Should set the defaults, typically just the scorer
     */
    public abstract void useDefaults();

    private List<DogeCVScorer> scorers = new ArrayList<>();
    private Size initSize;
    private Size adjustedSize;
    private Mat workingMat = new Mat();
    public double maxDifference = 10;

    public Point cropTLCorner = null; //The top left corner of the image used for processing
    public Point cropBRCorner = null; //The bottom right corner of the image used for processing

    public DogeCV.DetectionSpeed speed = DogeCV.DetectionSpeed.BALANCED;
    public double downscale = 0.5;
    public Size   downscaleResolution = new Size(640, 480);
    public boolean useFixedDownscale = true;
    protected String detectorName = "DogeCV Detector";

    public DogeCVDetector(){

    }

    /**
     * Sets the speed of the detector
     * @param speed the speed to set
     */
    public void setSpeed(DogeCV.DetectionSpeed speed){
        this.speed = speed;
    }

    /**
     * Gives the detector a new scorer
     *
     * @param newScorer
     */
    public void addScorer(DogeCVScorer newScorer){
        scorers.add(newScorer);
    }

    /**
     * Calculates the score for a given input mat
     * @param input the mat to calculate the score for
     * @return the score of the input mat
     */
    public double calculateScore(Mat input){
        double totalScore = 0;

        for(DogeCVScorer scorer : scorers){
            totalScore += scorer.calculateScore(input);
        }

        return totalScore;
    }


    /**
     * Returns a frame to display on the RC phone
     * @return the altered frame with version, speed, etc
     */
    @Override
    public Mat processFrame(Mat rgba, Mat gray) {
        initSize = rgba.size();

        if(useFixedDownscale){
            adjustedSize = downscaleResolution;
        }else{
            adjustedSize = new Size(initSize.width * downscale, initSize.height * downscale);
        }

        rgba.copyTo(workingMat);

        if(workingMat.empty()){
            return rgba;
        }
        Imgproc.resize(workingMat, workingMat,adjustedSize); // Downscale
        workingMat = MathFTC.crop(workingMat, cropTLCorner, cropBRCorner);

        Imgproc.resize(process(workingMat),workingMat,getInitSize()); // Process and scale back to original size for viewing
        //Print Info
        Imgproc.putText(workingMat,"DogeCV 2019.1 " + detectorName + ": " + getAdjustedSize().toString() + " - " + speed.toString() ,new Point(5,30),0,0.5,new Scalar(0,255,255),2);

        return workingMat;
    }

    public Size getInitSize() {
        return initSize;
    }

    public Size getAdjustedSize() {
        return adjustedSize;
    }

    public void setAdjustedSize(Size size) { this.adjustedSize = size; }
}
