package com.bridgelabz;

/**
 * Purpose: A class for owner of the parking lot system.
 *
 * @author :Ashwith
 * @since :10/11/21
 */
public class ParkingLotOwner {
    private boolean isFullCapacity;

    /**
     * This method is to say that the capacity is full.
     */
    public void capacityIsFull() {
        isFullCapacity = true;
    }

    /**
     * This method is to quarry if the capacity of parking lot is
     * full or not.
     *
     * @return boolean - true if capacity is full else false.
     */
    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
