package com.bridgelabz;

import java.util.List;

public class PoliceDepartment {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    public List getAllWhiteVehicles() throws ParkingLotException{
        return parkingLotSystem.getWhiteColorVehiclePosition();
    }

    public List getBlueToyotaVehicles(){
        return parkingLotSystem.getBlueToyotaVehicles();
    }
}
