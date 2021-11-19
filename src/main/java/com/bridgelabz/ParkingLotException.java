package com.bridgelabz;
/*******************************************************************************************
 * Purpose: A custom exception class to handle exceptions in the program.
 *
 * @author Ashwith
 * @since 10/11/21
 *******************************************************************************************/
public class ParkingLotException extends RuntimeException {
    private final ExceptionType exceptionType;

    //constructor to initialize variables.
    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.exceptionType = type;
    }

    /**
     * Enum to define the type of exception.
     */
    public enum ExceptionType {
        LOT_IS_FULL, VEHICLE_NOT_FOUND, VEHICLE_ALREADY_PARKED
    }
}
