
package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ArmLift.Enums.ClawPosition;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.StrafeDrive;

//Level 4 in Slideshow
//@Disabled
@Autonomous(name = "Specimen, Push Block, & Park", group="spec")
public class SpecPark extends LinearOpMode {

    Robot robot;
    StrafeDrive driving;

    LinearOpMode opMode;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);
        driving = robot.driving;

        robot.fullLift.claw.moveClaw(ClawPosition.CLOSE);

        waitForStart();

        if (opModeIsActive()) {

            while((!robot.topLiftLim.isPressed() || !robot.topDrawLim.isPressed())  && opModeIsActive()) {
                robot.fullLift.cascade.Go(1);
                robot.fullLift.drawBridge.Go(1);
            }


            robot.fullLift.drawBridge.Go(0);

            //get specific distance while moving at a slower pace

            sleep(5000);
            telemetry.addData("back distance:", robot.backDistance.getDistance());
            telemetry.update();

            while (robot.backDistance.isDistanceLess(63) && opModeIsActive()) {
                driving.vertical(.5);
                telemetry.addData("back distance:", robot.backDistance.getDistance());
                telemetry.update();
            }
            driving.stop();

            sleep(5000);

            robot.fullLift.cascade.Go(-1);
            sleep(2000);

            robot.fullLift.cascade.Go(0);

            robot.fullLift.claw.moveClaw(ClawPosition.OPEN);

            //moving back to the wall after placing the spec

            while (robot.backDistance.isDistanceGreater(10)) {
                driving.vertical(-0.5);
                telemetry.addData("back distance:", robot.backDistance.getDistance());
                telemetry.update();
            }

            //moving to observation zone
            driving.horizontal(0.7);
            sleep(5000);
            driving.horizontal(0.5);
            while(!(robot.leftColor.redOrBlueTape() || robot.rightColor.redOrBlueTape())){
                telemetry.addData("tape","not found");
                telemetry.update();
                sleep(20);
            }

            robot.driving.stop();

        }
    }

}

