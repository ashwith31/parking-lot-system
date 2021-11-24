package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/********************************************************************************
 * Purpose: This class is to do activities related to Police Department.
 *
 * @author Ashwith
 * @since 21/11/21
 *******************************************************************************/
public class PoliceDepartment {

    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is to find all the white vehicles that are present in the parking lots.
     *
     * @return List of white vehicles.
     * @throws ParkingLotException when there are no white vehicles present in
     *                             the parking lots.
     */
    public List getAllWhiteVehicles() throws ParkingLotException {
        return parkingLotSystem.getWhiteColorVehiclePosition();
    }

    /**
     * This method is to find all the Blue Toyota vehicles that are present in the parking lots.
     *
     * @return List of Blue Toyota vehicles.
     * @throws ParkingLotException when there are no Blue Toyota vehicles present in
     *                             the parking lots.
     */
    public List getBlueToyotaVehicles() throws ParkingLotException {
        return parkingLotSystem.getBlueToyotaVehicles();
    }

    /**
     * This method is to find all the BMW vehicles that are present in the parking lots.
     *
     * @return List of BMW vehicles.
     * @throws ParkingLotException when there are no BMW vehicles present in
     *                             the parking lots.
     */
    public List getAllBMWVehicles() throws ParkingLotException {
        return parkingLotSystem.getBMWVehicles();
    }

    /**
     * This method is to get all the Vehicles that are parked by handicapped
     * people.
     *
     * @return List of handicapped vehicles
     * @throws ParkingLotException if there are no handicapped vehicles in the parking lot.
     */
    public List getAllHandicappedVehicles() throws ParkingLotException {
        return parkingLotSystem.getHandicappedVehicles();
    }

    /**
     * This method is to validate the vehicle's number plate if it is
     * correct or not.
     *
     * @return List of all vehicles that are having invalid number plate.
     * @throws ParkingLotException if there are no invalid vehicles present in parking lots.
     */
    public List vehicleNumberValidate() {
        List<Vehicle> parkedVehicles = new ArrayList();
        List<Vehicle> temp = new ArrayList();
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{2,4}$");
        parkedVehicles = parkingLotSystem.getAllVehicles();
        for (Vehicle vehicle : parkedVehicles) {
            Matcher matcher = pattern.matcher(vehicle.getNumberPlate());
            if (matcher.matches() == false)
                temp.add(vehicle);
        }
        if (temp.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Invalid Vehicles Present");
        return temp;
    }

    /**
     * This method is to get all the vehicles that are parked
     * before 30 minutes.
     *
     * @return List of vehicles that are parked before 30 minutes
     * @throws ParkingLotException if there are no vehicles that are parked before
     *                             30 minutes.
     */
    public List getAllVehiclesParkedBefore30Minutes() throws ParkingLotException {
        return parkingLotSystem.getVehiclesParkedBefore30Minutes();
    }
}
