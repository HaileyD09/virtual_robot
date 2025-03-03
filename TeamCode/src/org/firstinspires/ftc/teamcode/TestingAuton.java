package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "testing")
public class TestingAuton extends LinearOpMode {

    Robot robot;

    LinearOpMode opMode;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Back Distance:", robot.backDistance.getDistance());
            telemetry.addData("Right Distance:", robot.rightDistance.getDistance());
            telemetry.addData("Left Distance:", robot.leftDistance.getDistance());

            telemetry.addData("Right Color Red:", robot.rightColor.getRed());
            telemetry.addData("Left Color Red:", robot.rightColor.getRed());

            telemetry.update();
        }

    }
}