package com.disnodeteam.dogecv;

import android.util.Log;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class DogeCV {
    static List<Mat> channels = new ArrayList<>();
    // RED FILTER
    public static void leviRedFilter (Mat input, Mat mask){


        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2Lab);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);
        Core.split(input, channels);
        Imgproc.threshold(channels.get(1), mask, 164, 255, Imgproc.THRESH_BINARY);

        for(int i=0;i<channels.size();i++){
            channels.get(i).release();
        }

        input.release();

    }

    public static void leviRedFilter (Mat input, Mat mask, double threshold){


        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2Lab);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);
        Core.split(input, channels);
        Imgproc.threshold(channels.get(1), mask, threshold, 255, Imgproc.THRESH_BINARY);

        for(int i=0;i<channels.size();i++){
            channels.get(i).release();
        }
    }


    // BLUE FILTER

    public static void leviBlueFilter (Mat input, Mat mask){
        List<Mat> channels = new ArrayList<>();

        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2Lab);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);
        Core.split(input, channels);
        Imgproc.threshold(channels.get(1), mask, 145, 255, Imgproc.THRESH_BINARY);

        for(int i=0;i<channels.size();i++){
            channels.get(i).release();
        }
    }

    public static void leviBlueFilter (Mat input, Mat mask, double threshold){
        List<Mat> channels = new ArrayList<>();

        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YUV);
        Imgproc.GaussianBlur(input,input,new Size(3,3),0);
        Core.split(input, channels);
        Imgproc.threshold(channels.get(1), mask, threshold, 255, Imgproc.THRESH_BINARY);

        for(int i=0;i<channels.size();i++){
            channels.get(i).release();
        }
    }
}
