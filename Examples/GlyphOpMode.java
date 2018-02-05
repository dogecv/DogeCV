package org.firstinspires.ftc.testing;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.*;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="DogeCV Glyph Detector", group="DogeCV")

public class GlyphOpMode extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();


     private GlyphDetector glyphDetector = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");


        glyphDetector = new GlyphDetector();
        glyphDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        glyphDetector.minScore = 1;
        glyphDetector.downScaleFactor = 0.3;
        glyphDetector.speed = GlyphDetector.GlyphDetectionSpeed.SLOW;
        glyphDetector.enable();

        telemetry.update();
    }

    @Override
    public void start() {
        runtime.reset();


    }

    @Override
    public void loop() {



        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Glyph Pos X", glyphDetector.getChosenGlyphOffset());
        telemetry.addData("Glyph Pos Offest", glyphDetector.getChosenGlyphPosition().toString());
        telemetry.update();


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        glyphDetector.disable();
    }

}
