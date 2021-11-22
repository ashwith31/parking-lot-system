package com.bridgelabz;

import java.util.List;

public class PoliceDepartment {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is to find all the white vehicles that are present in the parking lots.
     *
     * @return List of white vehicles.
     * @throws ParkingLotException when there are no white vehicles present in
     * the parking lots.
     */
    public List getAllWhiteVehicles() throws ParkingLotException{
        return parkingLotSystem.getWhiteColorVehiclePosition();
    }

    /**
     * This method is to find all the Blue Toyota vehicles that are present in the parking lots.
     *
     * @return List of Blue Toyota vehicles.
     * @throws ParkingLotException when there are no Blue Toyota vehicles present in
     * the parking lots.
     */
    public List getBlueToyotaVehicles() throws ParkingLotException{
        return parkingLotSystem.getBlueToyotaVehicles();
    }

    /**
     * This method is to find all the BMW vehicles that are present in the parking lots.
     *
     * @return List of BMW vehicles.
     * @throws ParkingLotException when there are no BMW vehicles present in
     * the parking lots.
     */
    public List getAllBMWVehicles() throws ParkingLotException{
        return parkingLotSystem.getBMWVehicles();
    }
}
