package com.bridgelabz;

/***************************************************************************
 * Purpose: This class has all the operations of the Parking Lot.
 *
 * @author Ashwith
 * @since 9/11/21
 ***************************************************************************/
public class ParkingLotSystem {
    private Object vehicle;

    /**
     * Purpose: This method is used to make a vehicle park in the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is parked
     * else false
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException("Parking Lot is full");
        this.vehicle = vehicle;
    }

    /**
     * Purpose: This method is used to make a vehicle un-park from the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is un-parked
     * else false
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        if (this.vehicle == null)
            throw new ParkingLotException("There is no vehicle to un-park");
        if (this.vehicle.equals(vehicle))
            this.vehicle = null;
    }

    /**
     * This method is used to check if the vehicle is parked or not.
     *
     * @param vehicle
     * @return boolean true if vehicle is parked or else false.
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicle.equals(vehicle);
    }

    /**
     * This method is used to check if the vehicle is un-parked or not.
     *
     * @return boolean true if vehicle is un-parked or else false.
     */
    public boolean isVehicleUnParked() {
        return this.vehicle == null;
    }
}
