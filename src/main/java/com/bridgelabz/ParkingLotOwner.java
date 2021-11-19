package com.bridgelabz;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Purpose: A class for owner of the parking lot system.
 *
 * @author :Ashwith
 * @since :10/11/21
 */
public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;
    static Map<Object, LocalDateTime> localDateTimeMap = new HashMap<>();

    /**
     * This method is to say that the capacity is full.
     */
    @Override
    public void capacityIsFull() {
        isFullCapacity = true;
    }
    /**
     * This method is to find if the capacity is available in the parking lot.
     */
    @Override
    public void capacityIsAvailable() {
        isFullCapacity = false;
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

    /**
     * Purpose: For storing Time of vehicle when vehicle is parked.
     *
     * @param vehicle object for mapping time and date.
     * @return LocalDateTime of the parked vehicle
     */
    public static LocalDateTime parkedTimeOfVehicle(Object vehicle) {
        LocalDateTime now = LocalDateTime.now();
        localDateTimeMap.put(vehicle, now);
        return now;
    }
}
