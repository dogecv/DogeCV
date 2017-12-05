
package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.*;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


import java.io.IOException;


@TeleOp(name="DogeCV Red Cryptobox Detector", group="DogeCV")

public class DogeCVRedCrypto extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();


     private CryptoboxDetectorRed cryptoboxDetectorRed = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");


        cryptoboxDetectorRed = new CryptoboxDetectorRed();
        cryptoboxDetectorRed.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        //Optional Test Code to load images via Drawables
        //cryptoboxDetectorRed.UseImportedImage = false;
        //cryptoboxDetectorRed.SetTestMat(com.qualcomm.ftcrobotcontroller.R.drawable.test_cv4);

        cryptoboxDetectorRed.enable();


    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {
        runtime.reset();


    }

    @Override
    public void loop() {

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("isCryptoBoxDetected", cryptoboxDetectorRed.isCryptoBoxDetected());
        telemetry.addData("isColumnDetected ",  cryptoboxDetectorRed.isColumnDetected());

        telemetry.addData("Column Left ",  cryptoboxDetectorRed.getCryptoBoxLeftPosition());
        telemetry.addData("Column Center ",  cryptoboxDetectorRed.getCryptoBoxCenterPosition());
        telemetry.addData("Column Right ",  cryptoboxDetectorRed.getCryptoBoxRightPosition());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        cryptoboxDetectorRed.disable();
    }

}
