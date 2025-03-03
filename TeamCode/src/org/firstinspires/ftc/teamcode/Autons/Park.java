package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

//Level 1 Auton in Slideshow
@Autonomous(name = "Close Park (red or blue)", group = "park")
public class Park extends LinearOpMode {

    Robot robot;

    LinearOpMode opMode;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        if (opModeIsActive()) {
            robot.driving.vertical(0.5f);
            sleep(2000);
            while(!(robot.leftColor.redOrBlueTape() || robot.rightColor.redOrBlueTape())){
                telemetry.addData("tape","not found");
                telemetry.update();
                robot.driving.turn(0.5);
                robot.driving.vertical(0.35f);
                sleep(10);
            } 

            robot.driving.stop();
        }

    }
}