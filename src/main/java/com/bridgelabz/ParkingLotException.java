package com.bridgelabz;

/**
 * Purpose: A custom exception class to handle exceptions created in the program.
 *
 * @author Ashwith
 * @since 10/11/21
 */
public class ParkingLotException extends Exception{
    public ParkingLotException(String message) {
        super(message);
    }
}
