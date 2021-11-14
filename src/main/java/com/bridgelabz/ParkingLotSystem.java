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
    private  int actualCapacity;
    private static List vehicles;
    private static List<ParkingLotObserver> observers;

    public ParkingLotSystem() {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
    }

    /**
     * This method is used to register the owner of parking lot.
     *
     * @param observer - The observer of the parking lot.
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose: This method is used to make a vehicle park in the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is parked
     * else false
     */
    public void park(Object vehicle) throws ParkingLotException {
        if(isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle is already parked");
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observers: observers) {
                    observers.capacityIsFull();
            }
        }
        this.vehicles.add(vehicle);
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
        if (this.vehicles.contains(vehicle)){
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observers: observers) {
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
        if( this.vehicles.contains(vehicle))
            return true;
        return false;
    }

    /**
     * This method is used to check if the vehicle is un-parked or not.
     *
     * @return boolean true if vehicle is un-parked or else false.
     */
    public boolean isVehicleUnParked(Object vehicle) {
        if( this.vehicles.contains(vehicle)) {
            return false;
        }
        return true;
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
