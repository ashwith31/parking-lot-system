package com.bridgelabz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/***************************************************************************
 * Purpose: This class has all the operations of the Parking Lot.
 *
 * @author Ashwith
 * @since 9/11/21
 ***************************************************************************/

public class ParkingLotSystem {
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;
    private static List<Vehicle> parkingLot1;
    private static List<Vehicle> parkingLot2;
    private static List<Vehicle> parkingLotForHandicapped;

    //constructor to initialize variables.
    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLot1 = new ArrayList<>();
        parkingLot2 = new ArrayList<>();
        parkingLotForHandicapped = new ArrayList<>();
    }

    /**
     * This method is used to set the capacity of the parking lot.
     *
     * @param capacity - size of the parking lot.
     */
    public void setCapacityOfParkingLot(int capacity) {
        actualCapacity = capacity;
    }

    /**
     * This method is used to register the observers of parking lot.
     *
     * @param observer - The observer of the parking lot.
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose: This method is used to make a vehicle park in the Parking Lot.
     *
     * @param vehicle object to be parked
     * @throws ParkingLotException if the vehicle is already parked or if the lot
     *                             is full.
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED,
                    "Vehicle is already parked");
        if (parkingLot1.size() <= actualCapacity && parkingLot2.size() <= actualCapacity
                || parkingLotForHandicapped.size() <= actualCapacity) {
            if (vehicle.getPersonType() == Vehicle.PersonType.HANDICAP) {
                parkingLotForHandicapped.add(vehicle);
            } else if (parkingLot1.size() > parkingLot2.size()) {
                parkingLot2.add(vehicle);
            } else {
                parkingLot1.add(vehicle);
            }
        }
        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity
                                || parkingLotForHandicapped.size() == actualCapacity) {
            for (ParkingLotObserver observers : observers) {
                observers.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.LOT_IS_FULL, "The Parking Lot is Full.");
        }
    }

    /**
     * Purpose: This method is used to make a vehicle un-park from the Parking Lot.
     *
     * @param vehicle - object to be un-parked
     * @return boolean - True if the vehicle is un-parked
     * else false
     * @throws ParkingLotException if there is a null or nothing to be un-parked.
     */
    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle)) {
                parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle)) {
                parkingLot2.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle)) {
                parkingLotForHandicapped.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException
                (ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "There is no such vehicle");
    }


    /**
     * This method is used to check if the vehicle is parked or not.
     *
     * @param vehicle object to be checked if parked or not.
     * @return boolean true if vehicle is parked or else false.
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        boolean isParked = false;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        return isParked;
    }

    /**
     * This method is used to check if the vehicle is un-parked or not.
     *
     * @param vehicle object to be checked if un-parked or not.
     * @return boolean true if vehicle is un-parked or else false.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        boolean isUnParked = true;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        return isUnParked;
    }

    /**
     * This method is used to find the vehicle in the parking lot.
     *
     * @param vehicle object to find.
     * @return object of vehicle if present.
     * @throws ParkingLotException if there is no such vehicle as passed in
     *                             the parameter in the parking lots.
     */
    public Vehicle findVehicle(Vehicle vehicle) throws ParkingLotException {
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        for (Vehicle vehicle1 : parkingLotForHandicapped) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "No Such Vehicle Found");
    }

    /**
     * This method is to find all the white vehicles that are present in the parking lots.
     *
     * @return List of white vehicles.
     * @throws ParkingLotException when there are no white vehicles present in
     *                             the parking lots.
     */
    public List getWhiteColorVehiclePosition() throws ParkingLotException {
        ArrayList temp = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getColor().equals("White"))
                temp.add("ParkingLot1: " + parkingLot1.indexOf(vehicle));
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getColor().equals("White"))
                temp.add("ParkingLot2: " + parkingLot2.indexOf(vehicle));
        }
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getColor().equals("White"))
                temp.add("HandicapParkingLot: " + parkingLot2.indexOf(vehicle));
        }
        if (temp.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No White Color Vehicle Found");
        return temp;
    }

    /**
     * This method is to find all the Blue Toyota vehicles that are present in the parking lots.
     *
     * @return List of Blue Toyota vehicles.
     * @throws ParkingLotException when there are no Blue Toyota vehicles present in
     *                             the parking lots.
     */
    public List getBlueToyotaVehicles() throws ParkingLotException {
        ArrayList temp = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getName().equals("Toyota") && vehicle.getColor().equals("Blue")) {
                temp.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                        vehicle.getNumberPlate() + " Location = ParkingLot 1: " + parkingLot1.indexOf(vehicle));
            }
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getName().equals("Toyota") && vehicle.getColor().equals("Blue")) {
                temp.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                        vehicle.getNumberPlate() + " Location = ParkingLot 2: " + parkingLot2.indexOf(vehicle));
            }
        }
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getName().equals("Toyota") && vehicle.getColor().equals("Blue")) {
                temp.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                        vehicle.getNumberPlate() + " Location = HandicapLot : " +
                        parkingLotForHandicapped.indexOf(vehicle));
            }
        }
        if (temp.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Blue Toyota Vehicle Found");
        return temp;
    }

    /**
     * This method is to find all the BMW vehicles that are present in the parking lots.
     *
     * @return List of BMW vehicles.
     * @throws ParkingLotException when there are no BMW vehicles present in
     *                             the parking lots.
     */
    public List getBMWVehicles() throws ParkingLotException {
        ArrayList temp = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getName().equals("BMW"))
                temp.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getName().equals("BMW"))
                temp.add(vehicle);
        }
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getName().equals("BMW"))
                temp.add(vehicle);
        }
        if (temp.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No BMW Vehicles Found");
        return temp;
    }

    /**
     * This method is to find all the vehicles of handicapped persons
     * that are present in the parking lots.
     *
     * @return List of vehicles of handicapped persons.
     * @throws ParkingLotException when there are no vehicles of
     *                             handicapped persons present in the parking lots.
     */
    public List getHandicappedVehicles() {
        ArrayList temp = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getVehicleType() == Vehicle.VehicleType.SMALL)
                temp.add(vehicle);
        }
        if (temp.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Vehicles Found");
        return temp;
    }

    /**
     * This method is to return all the vehicles that are parked in the
     * parking lots.
     *
     * @return List of all vehicles parked in parking lots.
     * @throws ParkingLotException if there are no vehicles present in the
     *                             parking lots.
     */
    public List getAllVehicles() {
        ArrayList temp = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicapped) {
            temp.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot1) {
            temp.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            temp.add(vehicle);
        }
        if (temp.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No Vehicles Present");
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
    public List getVehiclesParkedBefore30Minutes() {
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(30);
        ArrayList temp = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicapped) {
            if (vehicle.getDateTime().compareTo(localDateTime) <= 0)
                temp.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getDateTime().compareTo(localDateTime) <= 0)
                temp.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getDateTime().compareTo(localDateTime) <= 0)
                temp.add(vehicle);
        }
        if (temp.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND,
                    "No vehicles found");
        return temp;
    }
}
