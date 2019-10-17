/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.dogecv;

import android.view.View;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.Dogeforia;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldDetector;
import com.disnodeteam.dogecv.filters.LeviColorFilter;
import com.disnodeteam.dogecv.math.MathFTC;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;

/**
 *This is an example of how to use the Gold and the VuMark detectors simultaneously. See OpenCVPipeline for more documentation on the relevant methods
 */

@TeleOp(name="Gold & VuMarks Example", group="DogeCV")

public class GoldAndVuMarkExample extends OpMode {

    //Detector object
    GoldDetector detector;

    @Override
    public void init() {
        telemetry.addData("Status", "DogeCV 2019.1 - Gold & VuMark Example");
        detector = new GoldDetector(); // Create a Gold Detector

        //Sets the Vuforia license key. ALWAYS SET BEFORE INIT!
        detector.VUFORIA_KEY = "---INSERT YOUR KEY HERE---";
        
        detector.setCAMERA_FORWARD_DISPLACEMENT(4); //eg: Camera is 4 inches in front of robot center
        detector.setCAMERA_LEFT_DISPLACEMENT(0); //eg: Camera is ON the robot's center line
        detector.setCAMERA_VERTICAL_DISPLACEMENT(6); //eg: Camera is 6 inches above the ground

        //Inits the detector. Choose which camera to use, and whether to detect VuMarks here
        detector.init(hardwareMap.appContext,CameraViewDisplay.getInstance(), DogeCV.CameraMode.BACK, true);

        //Basic detector settings
        detector.useDefaults(); // Use default settings
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // Uncomment if using PERFECT_AREA scoring
        detector.enable();
        detector.enableVuMarkDetection();
    }

    /*
     * Code to run REPEATEDLY when the driver hits INIT
     */
    @Override
    public void init_loop() {
        telemetry.addLine("Waiting for start...");
        telemetry.update();
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY when the driver hits PLAY
     */
    @Override
    public void loop() {
        //Telemetry feed from VuMark Detector
        if(detector.isVuMarkVisible()) { //Checks if a VuMark is visible right now
            telemetry.addData("Visible Target", detector.findVuMark().name()); //Retrieves the name of the current VuMark
            VectorF translation = detector.getRobotTranslation(); //Obtains current robot location, as a vector in inches
            if(translation != null) {
                telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                        translation.get(0), translation.get(1), translation.get(2));
            }
            Orientation rotation = detector.getRobotOrientation(); //Obtains current robot orientation, as a set of angles in degrees
            if(rotation != null) {
                telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
            }
        }
        else {
            //No visible VuMark
            telemetry.addData("Visible Target", "none");
        }
        //Gold telemetry feed
        telemetry.addData("Gold isFound: ", detector.isFound());
        Rect rect = detector.getFoundRect();
        if(detector.isFound()) telemetry.addData("Location: ", Integer.toString((int) (rect.x + rect.width*0.5)) + ", " + Integer.toString((int) (rect.y+0.5*rect.height)));

        // Update telemetry
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        detector.disable();
        super.stop();
    }

}
