package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ArmLift.Enums.ClawPosition;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")
public class TeleOp extends LinearOpMode {

    Robot robot;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();

        while (opModeIsActive()){

            //_______________________________________________
            //             MAIN CONTROLLER
            //_______________________________________________
            
            float x = gamepad1.right_stick_x;
            float y = -gamepad1.right_stick_y; //inputs from joystick are opposite
            float t = gamepad1.left_stick_x;

            robot.driving.joystickDrive(x, y, t);


            //_______________________________________________
            //             MECH CONTROLLER
            //_______________________________________________

            //joystick controls

            //have to fix cascade
            robot.fullLift.cascade.Go(gamepad2.left_stick_y);
            robot.fullLift.drawBridge.Go(gamepad2.right_stick_y); //making it go up when pushing up

            //levels - not tested yet
/*            if (gamepad1.x)
                robot.fullLift.moveLiftToPosition(FullArmLift.LIFT_POSITION.RESET);
            if (gamepad1.a)
                robot.fullLift.moveLiftToPosition(FullArmLift.LIFT_POSITION.HIGHRUNG);
            if (gamepad1.b)
                robot.fullLift.moveLiftToPosition(FullArmLift.LIFT_POSITION.LOWRUNG);
                */

            //claw code
            if (gamepad2.right_bumper)
                robot.fullLift.claw.moveClaw(ClawPosition.CLOSE);
            if (gamepad2.left_bumper)
                robot.fullLift.claw.moveClaw(ClawPosition.OPEN);
            if (!gamepad2.left_bumper && !gamepad2.right_bumper)
                robot.fullLift.claw.moveClaw(ClawPosition.STOP);

            //_______________________________________________
            //             PRINT STATEMENTS
            //_______________________________________________


            telemetry.addLine("_________________WHEELS_________________");

            //joystick inputs
            telemetry.addData("x: ", x);
            telemetry.addData("y: ", y);
            telemetry.addData("t: ", t);

            //wheels powers

            /*
            robot.printWheelPowers();

            telemetry.addData("red", robot.rightColor.getRed());
            telemetry.addData("blue", robot.rightColor.getBlue());
            telemetry.addData("green", robot.rightColor.getGreen());
            */

            telemetry.addLine("____________LIFT MOTOR STATES____________");

            telemetry.addData("Cascade: ", robot.fullLift.cascade.motor.getMotorState());
            telemetry.addData("Drawbridge: ", robot.fullLift.drawBridge.motor.getMotorState());


            telemetry.addLine("______________LIFT POSITIONS______________");

            telemetry.addData("claw position: ", robot.fullLift.claw.getCurrentPosition());
            telemetry.addData("cascade position: ", robot.fullLift.cascade.motor.getCurrentPosition());
            telemetry.addData("drawbridge position: ", robot.fullLift.drawBridge.motor.getCurrentPosition());

            telemetry.addLine("_____________LIMIT SWITCHES_______________");
            telemetry.addData("top lift limit switch hit: ", robot.fullLift.cascade.isUpperHit());
            telemetry.addData("bottom lift limit switch hit: ", robot.fullLift.cascade.isBottomHit());

            telemetry.addData("top draw limit switch hit: ", robot.fullLift.drawBridge.isUpperHit());
            telemetry.addData("bottom draw limit switch hit: ", robot.fullLift.drawBridge.isBottomHit());

            telemetry.update();
        }
    }
}