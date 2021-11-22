package com.bridgelabz;

import java.util.List;

public class PoliceDepartment {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    public List getAllWhiteVehicles(){
        return parkingLotSystem.getWhiteColorVehiclePosition();
    }
}
