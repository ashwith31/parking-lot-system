package com.bridgelabz;

public class Vehicle {

    private final String name;
    private final String numberPlate;
    private final String colorOfCar;

    public Vehicle(String name, String numberPlate, String colorOfCar) {
        this.name = name;
        this.numberPlate = numberPlate;
        this.colorOfCar = colorOfCar;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getName() {
        return name;
    }

    public String getColorOfCar() {
        return colorOfCar;
    }
}
