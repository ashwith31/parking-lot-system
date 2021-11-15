package com.bridgelabz;
/**********************************************************************************************
 * Purpose: An observer interface which provides the functionality for the observer classes.
 *
 * @author Ashwith
 * @since 14/11/21
 ***********************************************************************************************/
public interface ParkingLotObserver {
    /**
     * This method is to say that the capacity is full.
     */
    public void capacityIsFull();

    /**
     * This method is to find if the capacity is available in the parking lot.
     */
    public void capacityIsAvailable();
}
