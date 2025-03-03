package org.firstinspires.ftc.teamcode;

/**
 * locations are from the POV of your alliance side
 * if on blue imagine you were standing looking at the field
 * then decide if you are blue left or right
 *
 * in most cases blue right red left and blue left  red right are the same code
 * so tehy have combined cases for some autons
 */
public enum FieldLocation {
    BLUE_RIGHT,
    BLUE_LEFT,
    RED_RIGHT,
    RED_LEFT,
    BLUE_RIGHT_RED_LEFT,
    BLUE_LEFT_RED_RIGHT,
    ALL_LOCATIONS
}
