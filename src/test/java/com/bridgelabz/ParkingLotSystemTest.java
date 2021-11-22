package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class ParkingLotSystemTest {
    Vehicle vehicle;
    ParkingLotSystem parkingLotSystem;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(2);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle));
    }

    @Test
    public void givenAVehicle_WhenLotIsFullParked_ShouldThrowException() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle("Nissan", "2356", "White", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
    }

    @Test
    public void givenNoVehicle_WhenUnParked_ShouldThrowException() {
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark(null));
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle("Nissan", "2356", "White", "Naresh");
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    void givenCapacityIs1_ShouldBeAbleToPark2Vehicles() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle("Nissan", "2356", "White", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle("Nissan", "2356", "White", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(2);
        ParkingLotOwner owner = new ParkingLotOwner();
        Vehicle vehicle2 = new Vehicle("Nissan", "2356", "White", "Naresh");
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenToParkVehicleByAttendant_ShouldReturnTrue() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenADriver_WhenWantsToFindVehicle_ShouldReturnTrue() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        ParkingLotDriver parkingLotDriver = new ParkingLotDriver();
        parkingLotSystem.setCapacityOfParkingLot(2);
        parkingLotSystem.park(vehicle);
        Object expectedVehicle = parkingLotDriver.searchVehicle(vehicle);
        Assertions.assertEquals(vehicle, expectedVehicle);
    }

    @Test
    void givenVehicles_WhenEvenlyParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle("Nissan", "2356", "White", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);
    }

    @Test
    void givenPoliceDepartment_WhenSearchForWhiteVehicles_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle("Mercedes", "5647", "White", "Naresh");
        Vehicle vehicle2 = new Vehicle("Nissan", "2352", "White", "Naresh");
        Vehicle vehicle3 = new Vehicle("Nissan", "2353", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle("Nissan", "2354", "White", "Naresh");
        Vehicle vehicle5 = new Vehicle("Nissan", "2355", "White", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add("ParkingLot1: 0");
        expectedList.add("ParkingLot1: 2");
        expectedList.add("ParkingLot2: 0");
        expectedList.add("ParkingLot2: 1");
        List actualList = policeDepartment.getAllWhiteVehicles();
        Assertions.assertEquals(actualList, expectedList);
    }

//    @Test
//    void givenBlue() {
//        PoliceDepartment policeDepartment = new PoliceDepartment();
//        parkingLotSystem.setCapacityOfParkingLot(10);
//        vehicle = new Vehicle("Mercedes", "5647", "Blue", "Naresh");
//        Vehicle vehicle2 = new Vehicle("Nissan", "2352", "White", "Naresh");
//        Vehicle vehicle3 = new Vehicle("Nissan", "2353", "Blue", "Naresh");
//        Vehicle vehicle4 = new Vehicle("Nissan", "2354", "White", "Naresh");
//        Vehicle vehicle5 = new Vehicle("Nissan", "2355", "Blue", "Naresh");
//        parkingLotSystem.park(vehicle);
//        parkingLotSystem.park(vehicle2);
//        parkingLotSystem.park(vehicle3);
//        parkingLotSystem.park(vehicle4);
//        parkingLotSystem.park(vehicle5);
//        List expectedMap = parkingLotSystem.getBlueToyotaVehicles();
//        System.out.println(expectedMap);
//    }
}
