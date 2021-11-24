package com.bridgelabz;

import java.time.LocalDateTime;

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
    private final PersonType personType;
    private final LocalDateTime dateTime;

    /**
     * This enum is to specify the type of Person who is parking the vehicle.
     */
    enum PersonType {
        NORMAL, HANDICAP
    }

    /**
     * This enum is to specify the type of Vehicle which is being parked in the parking lot.
     */
    enum VehicleType {
        SMALL, MEDIUM, LARGE
    }

    //constructor to initialize variables.
    public Vehicle(VehicleType type, PersonType personType, LocalDateTime dateTime, String name, String numberPlate, String color, String parkingAttendantName) {
        this.name = name;
        this.numberPlate = numberPlate;
        this.color = color;
        this.parkingAttendantName = parkingAttendantName;
        this.vehicleType = type;
        this.personType = personType;
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public PersonType getPersonType() {
        return personType;
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
}
