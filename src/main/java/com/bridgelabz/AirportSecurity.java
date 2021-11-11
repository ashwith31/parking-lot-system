package com.bridgelabz;

public class AirportSecurity implements ParkingLotObserver {
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
