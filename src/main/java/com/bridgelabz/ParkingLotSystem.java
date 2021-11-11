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
    private List vehicles;
    private ParkingLotOwner owner;

    public ParkingLotSystem(int capacity) {
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    /**
     * Purpose: This method is used to make a vehicle park in the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is parked
     * else false
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size() == this.actualCapacity) {
            owner.capacityIsFull();
            throw new ParkingLotException("Parking Lot is full");
        }
        if(isVehicleParked(vehicle))
            throw new ParkingLotException("Vehicle is already parked");
        this.vehicles.add(vehicle);
    }

    /**
     * Purpose: This method is used to make a vehicle un-park from the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is un-parked
     * else false
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        if (this.vehicles == null)
            throw new ParkingLotException("There is no vehicle to un-park");
        if (this.vehicles.equals(vehicle))
            this.vehicles = null;
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
            vehicle = null;
            return true;
        }
        return false;
    }

    /**
     * This method is used to register the owner of parking lot.
     *
     * @param owner - The actual owner of the parking lot.
     */
    public void registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
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
