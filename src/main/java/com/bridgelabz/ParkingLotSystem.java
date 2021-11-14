package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

/***************************************************************************
 * Purpose: This class has all the operations of the Parking Lot.
 *
 * @author Ashwith
 * @since 9/11/21
 ***************************************************************************/
public class ParkingLotSystem {
    private static List vehicles;
    private static List<ParkingLotObserver> observers;
    private int actualCapacity;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        vehicles = new ArrayList();
    }

    /**
     * This method is used to register the owner of parking lot.
     *
     * @param observer - The observer of the parking lot.
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose: This method is used to make a vehicle park in the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is parked
     * else false
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle is already parked");
        if (vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observers : observers) {
                observers.capacityIsFull();
            }
        }
        vehicles.add(vehicle);
    }

    /**
     * Purpose: This method is used to make a vehicle un-park from the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is un-parked
     * else false
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException("There is no vehicle to un-park");
        if (vehicles.contains(vehicle)) {
            vehicles.remove(vehicle);
            for (ParkingLotObserver observers : observers) {
                observers.capacityIsAvailable();
            }
            return true;
        }
        return false;
    }

    /**
     * This method is used to check if the vehicle is parked or not.
     *
     * @param vehicle
     * @return boolean true if vehicle is parked or else false.
     */
    public boolean isVehicleParked(Object vehicle) {
        return vehicles.contains(vehicle);
    }

    /**
     * This method is used to check if the vehicle is un-parked or not.
     *
     * @return boolean true if vehicle is un-parked or else false.
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return !vehicles.contains(vehicle);
    }

    /**
     * This method is used to find the vehicle in the parking lot.
     *
     * @param vehicle
     * @return object of vehicle if present.
     * @throws ParkingLotException
     */
    public Object findVehicle(Object vehicle) throws ParkingLotException {
        if (vehicles.contains(vehicle)) {
            return vehicle;
        }
        throw new ParkingLotException("Vehicle is not Present in the lot");
    }

    /**
     * This method is used to set the capacity of the parking lot.
     *
     * @param capacity - size of the parking lot.
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }
}
