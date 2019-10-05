package com.disnodeteam.dogecv;

/**
 * Contains global values and types
 */
public class DogeCV {
    /**
     * The possible speeds for detection, the faster the less accurate and the
     * slower the more accurate
     */
    public enum DetectionSpeed {
        VERY_FAST,
        FAST,
        BALANCED,
        SLOW,
        VERY_SLOW
    }

    /**
     * The possible ways to score
     */
    public enum AreaScoringMethod {
        MAX_AREA,
        PERFECT_AREA,
        COLOR_DEVIATION
    }

    /**
     * The different cameras that can be used
     */
    public enum CameraMode {
        BACK,
        FRONT,
        WEBCAM
    }

    /**
     * The different VuMarks for the Rover Ruckus challenge
     */
    public enum VuMark {
        NONE,
        BLUE_ROVER,
        RED_FOOTPRINT,
        FRONT_CRATERS,
        BACK_SPACE
    }
}
