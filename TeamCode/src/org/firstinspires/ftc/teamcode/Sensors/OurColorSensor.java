package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * In this file we:
 * define our own color sensor class
 * although there already is a color sensor class
 * using this format allows us to add our own functions like detecting tape
 * each color sensor will be initalized as an instance of the OurColorSensor object
 * so it will inheriet all the properites and functions in this class
 */
public class OurColorSensor {

    private ColorSensor sensor;

    //tape detection thresholds
    //these are set with default tested values
    private int redTapeThreshold;
    private int blueTapeThreshold;
    private int whiteTapeThreshold; //all colors are greater than this value to detect white tape

    public OurColorSensor(ColorSensor sensor) {
        this.sensor = sensor;
        redTapeThreshold = 2100;
        blueTapeThreshold = 3975;
        whiteTapeThreshold = 5000;
    }

    //tape detection
    public boolean redTape() { return sensor.red() > redTapeThreshold; }
    public boolean blueTape() { return sensor.blue() > blueTapeThreshold; }
    public boolean whiteTape() { return ((sensor.red() > whiteTapeThreshold) &&
            (sensor.green() > whiteTapeThreshold) && (sensor.blue() > whiteTapeThreshold)); }

    public boolean redOrBlueTape() { return redTape() ||blueTape(); }
    public boolean anyTape() { return redOrBlueTape() || whiteTape(); }


    //returning values from the color sensor
    public int getRed() { return sensor.red(); }
    public int getGreen() { return sensor.green(); }
    public int getBlue() { return sensor.blue(); }

}
