package com.bridgelabz;

/**************************************************************************************
 * Purpose: This class is for attendant to park the vehicle whenever required.
 *
 * @author Ashwith
 * @since 14/11/21
 *************************************************************************************/
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is used when we need to park a vehicle by attendant.
     *
     * @param vehicle - the vehicle to be parked.
     */
    public void parkVehicleByAttendant(Vehicle vehicle) {
        parkingLotSystem.park(vehicle);
    }
}
