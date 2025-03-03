package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.ArmLift.FullArmLift;
import org.firstinspires.ftc.teamcode.Sensors.OurColorSensor;
import org.firstinspires.ftc.teamcode.Sensors.OurDistanceSensor;
import virtual_robot.controller.VirtualRobotController;

/**
 * In this file we:
 * initalize all the sensors, motors, and libraries
 * the motors and sensors go here so we are only initializing them in one place in the whole repo
 * (to avoid mistakes and conflicts)
 * the libraries like IDriving and arm etc are intialized here so they are also only in one place
 * the libaries also ahve access to everything within the robot class like the motors if they are intialized in this way
 * when you create a new opMode you should only initlaize the robot class (by passing the opMode (by writing "this" in the parentheses)
 * you cannot access any of the sensors or motors outside of this class (because encapsulation and saefty)
 * you can only control things by using the libraries and the functions within them that are public
 */
public class Robot {

    /**
     * itializtion of all sensors and motors
     */
    //wheels
    //rf, rb, lf, lb
    private DcMotor rf;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor lb;

    //lift
    private DcMotorEx cascadeMotor;
    private DcMotorEx drawbridgeMotor;
    private CRServo clawServo;

    //sensors (ftc sensor objects)
    private DistanceSensor backDist;
    private DistanceSensor rightDist;
    private DistanceSensor leftDist;
    private ColorSensor rightCol;
    private ColorSensor leftCol;
    public DigitalChannel topLiftLim;
    public DigitalChannel bottomLiftLim;
    public DigitalChannel topDrawLim;
    public DigitalChannel botDrawLim;

    /**
     * itializtion of classes/objects
     */

    //sensor objects (our sensor objects)
    public OurDistanceSensor backDistance;
    public OurDistanceSensor rightDistance;
    public OurDistanceSensor leftDistance;
    public OurColorSensor rightColor;
    public OurColorSensor leftColor;

    //complex objects
    public FullArmLift fullLift;
    public StrafeDrive driving;

    //opMode
    private LinearOpMode opMode;

    /**
     * @param opMode pass by writing: new Robot(this);
     */
    public Robot(LinearOpMode opMode) {
        HardwareMap map = opMode.hardwareMap;
        this.opMode = opMode;

        //wheels
        rf = map.tryGet(DcMotor.class, "front_right_motor");
        rb = map.tryGet(DcMotor.class, "back_right_motor");
        lf = map.tryGet(DcMotor.class, "front_left_motor");
        lb = map.tryGet(DcMotor.class, "back_left_motor");

        rf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.REVERSE);

        //arm lift
        cascadeMotor = map.tryGet(DcMotorEx.class, "cascadeMotor");
        drawbridgeMotor = map.tryGet(DcMotorEx.class, "drawbridge");
        clawServo = map.tryGet(CRServo.class, "servo");

        drawbridgeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        cascadeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //sensors
        backDist = map.tryGet(VirtualRobotController.DistanceSensorImpl.class, "back_distance");
        rightDist = map.tryGet(VirtualRobotController.DistanceSensorImpl.class, "right_distance");
        leftDist = map.tryGet(VirtualRobotController.DistanceSensorImpl.class, "left_distance");
        rightCol = map.tryGet(ColorSensor.class, "rightCol");
        leftCol = map.tryGet(ColorSensor.class, "leftCol");

        topLiftLim = map.tryGet(DigitalChannel.class, "topLiftLimit");
        bottomLiftLim = map.tryGet(DigitalChannel.class, "bottomLiftLimit");
        topDrawLim = map.tryGet(DigitalChannel.class, "topDrawLimit");
        botDrawLim = map.tryGet(DigitalChannel.class, "botDrawLimit");

        //sensor objects
        backDistance = new OurDistanceSensor(backDist);
        rightDistance = new OurDistanceSensor(rightDist);
        leftDistance = new OurDistanceSensor(leftDist);
        rightColor = new OurColorSensor(rightCol);
        leftColor = new OurColorSensor(leftCol);

        //complex objects
        driving = new StrafeDrive(rf, rb, lf, lb, DcMotor.ZeroPowerBehavior.BRAKE);
        fullLift = new FullArmLift(cascadeMotor, drawbridgeMotor, clawServo, topLiftLim, bottomLiftLim, topDrawLim, botDrawLim);
    }

    public void printWheelPowers() {
        opMode.telemetry.addData("rf: ", rf.getPower());
        opMode.telemetry.addData("lf: ", lf.getPower());
        opMode.telemetry.addData("rb: ", rb.getPower());
        opMode.telemetry.addData("lb: ", lb.getPower());
    }

    public void printColorValues(OurColorSensor sensor) {
        opMode.telemetry.addData("red: ", sensor.getRed());
        opMode.telemetry.addData("green: ", sensor.getGreen());
        opMode.telemetry.addData("blue: ", sensor.getBlue());
    }

}
