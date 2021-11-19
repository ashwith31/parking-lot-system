package com.bridgelabz;

/**
 * Purpose: This class is for the driver of vehicle of the parking lot system.
 *
 * @author Ashwith
 * @since 14/11/21
 */
public class ParkingLotDriver {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is to search for the vehicle parked in the parking lot.
     *
     * @param vehicle to be searched
     * @return object - the resultant vehicle after searching.
     */
    public Object searchVehicle(Vehicle vehicle){
        return parkingLotSystem.findVehicle(vehicle);
    }
}
