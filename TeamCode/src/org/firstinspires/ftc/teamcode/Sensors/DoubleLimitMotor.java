package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.ArmLift.GenericLiftMotor;

public class DoubleLimitMotor {
    private TouchSensor topLimit;
    private TouchSensor bottomLimit;

    public final GenericLiftMotor motor;

    private boolean isReversed = false;

    public DoubleLimitMotor(TouchSensor topLimit, TouchSensor bottomLimit, GenericLiftMotor motor) {
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
        this.motor  = motor;
    }

    public void setReversed(boolean reversed)
    {
        this.isReversed = reversed;
    }
    public boolean isUpperHit() {return topLimit.isPressed();}
    public boolean isBottomHit() {return bottomLimit.isPressed();}

    /**
     * assuming that when input is -1 it goes up
     * @param input
     * @return
     */
    public boolean canGo(float input) {
        if(!isReversed)
        {
            if(input < 0 && isUpperHit()){
                return false;
            }
            if(input > 0 && isBottomHit()){
                return false;
            }
        }
        else
        {
            if(input > 0 && isUpperHit()){
                return false;
            }
            if(input < 0 && isBottomHit()){
                return false;
            }
        }
        return true;
    }
    public void Go (float input) {
        if (Math.abs(input) < 0.1f){
            input = 0;
        }
        if(!this.canGo(input)){
            motor.setMotorPower(0);
            return;
        }
        motor.setMotorPower(input);
    }
}