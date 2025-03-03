package org.firstinspires.ftc.teamcode.wildbots25;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "distance sensor test")
public class DistanceSensorTest extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("driving:", robot.backDistance.getDistance());

            sleep(5000);

            robot.driving.vertical(0.5);
        }
        telemetry.addData("distance:", robot.backDistance.getDistance());
    }
}