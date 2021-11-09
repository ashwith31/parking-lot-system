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
    public boolean park(Object vehicle) {
        if (this.vehicle == null) {
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    /**
     * Purpose: This method is used to make a vehicle un-park from the Parking Lot.
     *
     * @param vehicle
     * @return boolean - True if the vehicle is un-parked
     * else false
     */
    public boolean unPark(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
