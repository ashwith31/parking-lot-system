package com.bridgelabz;
/*****************************************************************************************
 * Purpose: This is a vehicle pojo class which is used to create the object of vehicles.
 *
 * @author Ashwith
 * @since 18/11/21
 *****************************************************************************************/
public class Vehicle {

    private final String name;
    private final String numberPlate;
    private final String color;
    private final String parkingAttendantName;
    private final VehicleType vehicleType;

    enum VehicleType{
        SMALL, MEDIUM, LARGE;
    }

    public String getName() {
        return name;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getColor() {
        return color;
    }

    public String getParkingAttendantName() {
        return parkingAttendantName;
    }

    //constructor to initialize variables.
    public Vehicle(VehicleType type, String name, String numberPlate, String color, String parkingAttendantName) {
        this.name = name;
        this.numberPlate = numberPlate;
        this.color = color;
        this.parkingAttendantName = parkingAttendantName;
        this.vehicleType = type;
    }
}
