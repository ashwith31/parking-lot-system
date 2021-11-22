package com.bridgelabz;

import java.util.List;

public class PoliceDepartment {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    public List getAllWhiteVehicles() throws ParkingLotException{
        return parkingLotSystem.getWhiteColorVehiclePosition();
    }

    public List getBlueToyotaVehicles() throws ParkingLotException{
        return parkingLotSystem.getBlueToyotaVehicles();
    }

    public List getAllBMWVehicles() throws ParkingLotException{
        return parkingLotSystem.getBMWVehicles();
    }
}
