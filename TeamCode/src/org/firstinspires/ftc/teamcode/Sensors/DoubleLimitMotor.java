package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.ArmLift.GenericLiftMotor;

public class DoubleLimitMotor {
    private DigitalChannel topLimit;
    private DigitalChannel bottomLimit;

    public final GenericLiftMotor motor;

    private boolean isReversed = false;

    public DoubleLimitMotor(DigitalChannel topLimit, DigitalChannel bottomLimit, GenericLiftMotor motor) {
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
        this.motor  = motor;
    }

    public void setReversed(boolean reversed)
    {
        this.isReversed = reversed;
    }
    public boolean isUpperHit() {return !topLimit.getState();}
    public boolean isBottomHit() {return !bottomLimit.getState();}

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
