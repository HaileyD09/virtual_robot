package org.firstinspires.ftc.teamcode.wildbots25.ArmLift;

import org.firstinspires.ftc.teamcode.wildbots25.Sensors.DoubleLimitMotor;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class FullArmLift {

    public final DoubleLimitMotor cascade;
    public final DoubleLimitMotor drawBridge;

    public final Claw claw;

    public FullArmLift(DcMotorEx cascadeMotor,
                       DcMotorEx drawbridgeMotor,
                       CRServo clawServo,
                       DigitalChannel topLiftLimit,
                       DigitalChannel botLiftLimit,
                       DigitalChannel topDrawLimit,
                       DigitalChannel botDrawLimit) {
        claw = new Claw(clawServo);
        cascade = new DoubleLimitMotor(botLiftLimit, topLiftLimit, new GenericLiftMotor(cascadeMotor,0.8, 200));
        drawBridge = new DoubleLimitMotor(topDrawLimit, botDrawLimit, new GenericLiftMotor(drawbridgeMotor, 0.8, 200));

        cascade.setReversed(true);
    }


    public void moveLiftToPosition (LIFT_POSITION pos){
        if(pos == LIFT_POSITION.RESET){
            cascade.motor.runToPosition(0);
            drawBridge.motor.runToPosition(0);
        }
        if(pos == LIFT_POSITION.HIGHRUNG){
            //motor.setTargetPosition(); test for encoder
        }
        if(pos == LIFT_POSITION.LOWRUNG){
            //motor.setTargetPosition(); test for encoder
        }
        //add one more for PICKINGUP if RESET does not work

    }



    public enum LIFT_POSITION {
        RESET,
        HIGHRUNG,
        LOWRUNG

    }
}