package com.disnodeteam.dogecv.detectors;

import com.disnodeteam.dogecv.OpenCVPipeline;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
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


public class CryptoboxDetectorBlue extends OpenCVPipeline {


    public Mat MatOverride = new Mat();
    public boolean UseImportedImage = false;

    private boolean CryptoBoxDetected = false;
    private boolean ColumnDetected = false;
    private int[] CryptoBoxPositions = new int[2];

    public void SetTestMat( int rId){
        try {
            Mat imported = Utils.loadResource(context, rId);
            Imgproc.cvtColor(imported,imported,Imgproc.COLOR_RGB2BGR);
            Imgproc.resize(imported,MatOverride, new Size(1280,960));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Mat raw  = new Mat();
    private Mat mask  = new Mat();
    private Mat hsv  = new Mat();
    private Mat structure  = new Mat();
    private Mat hierarchy = new Mat();
    Mat kernel = Mat.ones(4,4,CvType.CV_32F);
    @Override
    public Mat processFrame(Mat rgba, Mat gray) {

        rgba.copyTo(raw);



        if(UseImportedImage){
            raw = MatOverride.clone();
            rgba.release();
        }else {
            MatOverride.release();
        }


        Imgproc.resize(raw,raw,new Size(480,360));

        Mat tempBefore = raw.t();

        Core.flip(tempBefore, raw, 1); //mRgba.t() is the transpose

        tempBefore.release();


        List<MatOfPoint> contours = new ArrayList<>();
        List<Rect> boxes = new ArrayList<>();

        Imgproc.cvtColor(raw,hsv,Imgproc.COLOR_RGB2HSV);



        Imgproc.erode(hsv,hsv,kernel);
        Imgproc.dilate(hsv,hsv,kernel);
        Imgproc.blur(hsv,hsv,new Size(6,6));


        Scalar lower = new Scalar(90, 135, 25);
        Scalar upper = new Scalar(130, 250, 150);



        Core.inRange(hsv,lower,upper,mask);

        structure = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(1,40));
        Imgproc.morphologyEx(mask,mask,Imgproc.MORPH_CLOSE, structure);

        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        for(MatOfPoint c : contours) {
            if(Imgproc.contourArea(c) >= 100) { //Filter by area
                Rect column = Imgproc.boundingRect(c);
                int ratio = Math.abs(column.height / column.width);

                if(ratio > 1.5) { //Check to see if the box is tall
                    boxes.add(column); //If all true add the box to array
                }
            }
        }
        for(Rect box : boxes) {
            Imgproc.rectangle(raw,new Point(box.x,box.y),new Point(box.x+box.width,box.y+box.height),new Scalar(255,0,0),2);
        }

        Collections.sort(boxes, new Comparator<Rect>() {
            @Override
            public int compare(Rect rect, Rect t1) {
                if(rect.x > t1.x){
                    return 1;
                }else if(rect.x < t1.x){
                    return -1;
                }else{
                    return 0;
                }
            }
        });

        CryptoBoxDetected = boxes.size() >=4;
        if(CryptoBoxDetected){
            Point left = drawSlot(0,boxes);
            Point center = drawSlot(1,boxes);
            Point right = drawSlot(2,boxes);

            Imgproc.putText(raw, "Left", new Point(left.x - 10, left.y - 20), 0,0.8, new Scalar(0,255,255),2);
            Imgproc.circle(raw,left,5,new Scalar(0,255,255), 3);

            Imgproc.putText(raw, "Center", new Point(center.x - 10, center.y - 20), 0,0.8, new Scalar(0,255,255),2);
            Imgproc.circle(raw,center, 5,new Scalar(0,255,255), 3);

            Imgproc.putText(raw, "Right", new Point(right.x - 10, right.y - 20), 0,0.8, new Scalar(0,255,255),2);
            Imgproc.circle(raw,right, 5,new Scalar(0,255,255), 3);
        }else{
            for(int i=0;i<boxes.size() - 1;i++){
                Point collumn = drawSlot(i,boxes);
                Imgproc.circle(raw,collumn,5,new Scalar(0,255,255), 3);
            }
        }
        Mat tempAfter = raw.t();

        Core.flip(tempAfter, raw, 0); //mRgba.t() is the transpose

        tempAfter.release();


        Imgproc.resize(raw,raw, new Size(1280,960));

        return raw;


    }

    public Object getKey(List item) {
        return item.get(0);
    }
    public Point drawSlot(int slot, List<Rect> boxes){
        Rect leftColumn = boxes.get(slot); //Get the pillar to the left
        Rect rightColumn = boxes.get(slot + 1); //Get the pillar to the right

        int leftX = leftColumn.x; //Get the X Coord
        int rightX = rightColumn.x; //Get the X Coord

        int drawX = ((rightX - leftX) / 2) + leftX; //Calculate the point between the two
        int drawY = leftColumn.height + leftColumn.y; //Calculate Y Coord. We wont use this in our bot's opetation, buts its nice for drawing

        return new Point(drawX, drawY);
    }

    public ArrayList ones(int width, int height) {
        ArrayList output = new ArrayList();
        for(int i = 1; i <= height; i++) {
            ArrayList row = new ArrayList();
            for(int j = 1; i <= width; i++) {
                row.add(1);
            }
            output.add(row);
        }
        return output;
    }

    public int[] getCryptoBoxPositions() {
        return CryptoBoxPositions;
    }

    public int getCryptoBoxLeftPosition() {
        return CryptoBoxPositions[0];
    }

    public int getCryptoBoxCenterPosition() {
        return CryptoBoxPositions[1];
    }

    public int getCryptoBoxRightPosition() {
        return CryptoBoxPositions[2];
    }

    public boolean isCryptoBoxDetected() {
        return CryptoBoxDetected;
    }

    public boolean isColumnDetected() {
        return ColumnDetected;
    }
}
