package com.disnodeteam.dogecv.detectors;

import android.media.Image;

import com.disnodeteam.dogecv.OpenCVPipeline;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Victo on 11/25/2017.
 */

public class GlyphDetector extends OpenCVPipeline {

    public double ChosenGlyphPos = -1;
    public double ChosenGlyphOffset = 0;

    public boolean FoundRect = false;

    public Size ImageSize = new Size(480, 360);

    public boolean DebugShowPreprocessed = false;
    public boolean DebugShowFiltered     = false;
    public boolean DebugDrawStats        = false;
    public boolean DebugDrawCenter       = false;
    public boolean DebugDrawRects        = true;

    public double ScoreRatioWeight = 0.5;
    public double ScoreDistanceXWeight = 0.8;
    public double ScoreDistanceYWeight = 1;
    public double ScoreAreaWeight = 0.4;

    public double MinScore = 0.5;

    public Mat MatOverride = new Mat();


    public void SetTestMat( int rId){
        try {
            Mat imported = Utils.loadResource(context, rId);
            Imgproc.cvtColor(imported,imported,Imgproc.COLOR_RGB2BGR);
            Imgproc.resize(imported,MatOverride, new Size(1280,960));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //hardwareMap.appContext, com.qualcomm.ftcrobotcontroller.R.drawable.test_cv, CvType.CV_8UC4);
    }

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {
        Mat out = rgba.clone();
        Imgproc.resize(out,out, ImageSize);

        Mat processed = preProcessFrame(out);
        Mat mask = filterFrame(processed);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();

        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        hierarchy.release();

        double chosenScore = 0;
        Rect chosenRect = null;

        Collections.sort(contours, new Comparator<MatOfPoint>() {
            @Override
            public int compare(MatOfPoint matOfPoint, MatOfPoint t1) {
                if(Imgproc.contourArea(matOfPoint) > Imgproc.contourArea(t1)){
                    return -1;
                }else if(Imgproc.contourArea(matOfPoint) < Imgproc.contourArea(t1)){
                    return  1;
                } else{
                    return  0;
                }
            }
        });
        if(contours.size() <= 0){
            return rgba;
        }
        contours.remove(0); // Remove First Index which is usually a large square filling the entire screen,

        for(MatOfPoint c : contours) {

            if(Imgproc.contourArea(c) > 1000){
                Rect rect = Imgproc.boundingRect(c);

                double x = rect.x;
                double y = rect.y;
                double w = rect.width;
                double h = rect.height;

                Point centerPoint = new Point(x + ( w/2), y + (h/2));

                double cubeRatio = Math.max(Math.abs(h/w), Math.abs(w/h));

                double score = 100;

                double diffrenceFromPerfect = Math.abs(1 - cubeRatio);
                double scoreRatioPunishment = 1 - diffrenceFromPerfect;
                double scoreRatio = scoreRatioPunishment * ScoreRatioWeight;
                score *= scoreRatio;

                double distanceFromCenterX = (ImageSize.width / 2) - centerPoint.x;
                double distanceFromCenterY = ImageSize.height - centerPoint.y;
                distanceFromCenterX = Math.abs(distanceFromCenterX / ImageSize.width);
                distanceFromCenterY = Math.abs(distanceFromCenterY / ImageSize.height);

                double scoreDistanceFromCenterXPunishment = 1 - distanceFromCenterX;
                double scoreDistanceFromCenterYPunishment = 1 - distanceFromCenterY;

                double scoreDistanceFromCenterX = scoreDistanceFromCenterXPunishment * ScoreDistanceXWeight;
                double scoreDistanceFromCenterY = scoreDistanceFromCenterYPunishment * ScoreDistanceYWeight;

                score *= scoreDistanceFromCenterX;
                score *= scoreDistanceFromCenterY;

                double minArea = GetMinArea(contours);
                double maxArea = GetMaxArea(contours);
                double area = Imgproc.contourArea(c);
                double normalizedArea = (area - minArea) / (maxArea - minArea);
                double scoreAreaPunishment = normalizedArea;
                double scoreArea  =scoreAreaPunishment * ScoreAreaWeight;
                score *= scoreArea;

                if(chosenRect == null){
                    chosenRect = rect;
                    chosenScore = score;
                }

                if(score > chosenScore){
                    chosenRect = rect;
                    chosenScore = score;
                }

                if(DebugDrawRects){
                    Imgproc.rectangle(out,new Point(x,y), new Point((x+w),(y+h)),new Scalar(0,255,255),1);
                }

                if(DebugDrawStats){
                    Imgproc.putText(out,"Score: " + score, new Point(x+5,y+5),0,0.5, new Scalar(0,255,255));
                }
            }




        }
        if(chosenRect != null && chosenScore > MinScore){
            double x = chosenRect.x;
            double y = chosenRect.y;
            double w = chosenRect.width;
            double h = chosenRect.height;
            Imgproc.rectangle(out,new Point(x,y), new Point((x+w),(y+h)),new Scalar(0,255,0),3);


            ChosenGlyphPos = (x+(w/2));
            ChosenGlyphOffset = ImageSize.width - (x+(w/2)) ;

            FoundRect = false;
        }else{
            FoundRect = true;
        }
        Imgproc.resize(out,out,new Size(1280,960));

        return out;
    }


    public Mat preProcessFrame(Mat inputFrame){
        Mat processed = inputFrame.clone();
        Imgproc.resize(processed,processed, ImageSize);
        Imgproc.cvtColor(processed,processed,Imgproc.COLOR_RGBA2GRAY);
        return processed;
    }

    public Mat filterFrame(Mat inputFrame){
        Mat blurred = new Mat();
        Imgproc.blur(inputFrame,blurred,new Size(5,5));

        Mat blat = blurred.clone();
        Imgproc.bilateralFilter(blurred,blat,11,17,17);

        Mat edges = new Mat();
        Imgproc.Canny(blurred,edges,20,50.0);

        Mat structure = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_RECT, new Size(10,10));
        Imgproc.morphologyEx(edges,edges,Imgproc.MORPH_CLOSE,structure);

        return edges;
    }

    private double GetMaxArea(List<MatOfPoint> allConturs){
        double currentMax = 0;

        for (MatOfPoint c: allConturs){
            double area= Imgproc.contourArea(c);
            if(area>currentMax){
                currentMax = area;
            }
        }

        return currentMax;
    }

    private double GetMinArea(List<MatOfPoint> allConturs){
        double currentMax = Double.MAX_VALUE;

        for (MatOfPoint c: allConturs){
            double area= Imgproc.contourArea(c);
            if(area<currentMax){
                currentMax = area;
            }
        }

        return currentMax;
    }
}
