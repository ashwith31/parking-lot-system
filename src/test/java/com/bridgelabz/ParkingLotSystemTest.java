package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(2);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle));
    }

    @Test
    public void givenAVehicle_WhenLotIsFullParked_ShouldThrowException() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2356", "White", "Naresh");
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
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2356", "White", "Naresh");
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
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2356", "White", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2356", "White", "Naresh");
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
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(2);
        ParkingLotOwner owner = new ParkingLotOwner();
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2356", "White", "Naresh");
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenToParkVehicleByAttendant_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenADriver_WhenWantsToFindVehicle_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        ParkingLotDriver parkingLotDriver = new ParkingLotDriver();
        parkingLotSystem.setCapacityOfParkingLot(2);
        parkingLotSystem.park(vehicle);
        Object expectedVehicle = parkingLotDriver.searchVehicle(vehicle);
        Assertions.assertEquals(vehicle, expectedVehicle);
    }

    @Test
    void givenVehicles_WhenEvenlyParked_ShouldReturnTrue() {
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Black", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2356", "White", "Naresh");
        parkingLotSystem.setCapacityOfParkingLot(1);
        parkingLotSystem.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);
    }

    @Test
    void givenHandicappedPerson_WhenParkedVehicleByAttendantShouldReturnTrue() {
        parkingLotSystem.setCapacityOfParkingLot(5);
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.HANDICAP, LocalDateTime.now(),
                "Mercedes", "5647", "Blue", "Naresh");
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenPoliceDepartment_WhenSearchForWhiteVehicles_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "White", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2352", "White", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2353", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2354", "White", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2355", "White", "Naresh");
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

    @Test
    void givenPoliceDepartment_WhenThereAreNoWhiteVehicles_ShouldThrowException() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Yellow", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2352", "Green", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2353", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2354", "Black", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2355", "Orange", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assertions.assertThrows(ParkingLotException.class, () -> policeDepartment.getAllWhiteVehicles());
    }

    @Test
    void givenPoliceDepartment_WhenSearchForBlueToyotaVehicles_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Toyota", "5647", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2352", "White", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Toyota", "2353", "Blue", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2354", "White", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Toyota", "2355", "Blue", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add("Name of Parking Attendant = " + vehicle.getParkingAttendantName() + " Plate Number = " +
                vehicle.getNumberPlate() + " Location = ParkingLot 1: " + 0);
        expectedList.add("Name of Parking Attendant = " + vehicle3.getParkingAttendantName() + " Plate Number = " +
                vehicle3.getNumberPlate() + " Location = ParkingLot 1: " + 1);
        expectedList.add("Name of Parking Attendant = " + vehicle5.getParkingAttendantName() + " Plate Number = " +
                vehicle5.getNumberPlate() + " Location = ParkingLot 1: " + 2);
        List actualList = policeDepartment.getBlueToyotaVehicles();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void givenPoliceDepartment_WhenThereAreNoBlueToyotaVehicles_ShouldThrowException() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2352", "Green", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2353", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2354", "Black", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2355", "Orange", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assertions.assertThrows(ParkingLotException.class, () -> policeDepartment.getBlueToyotaVehicles());
    }

    @Test
    void givenPoliceDepartment_WhenBMWVehicles_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "BMW", "5647", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2352", "White", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "BMW", "2353", "Blue", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2354", "White", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Toyota", "2355", "Blue", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add(vehicle);
        expectedList.add(vehicle3);
        List actualList = policeDepartment.getAllBMWVehicles();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void givenPoliceDepartment_WhenThereAreNoBMWVehicles_ShouldThrowException() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Mercedes", "5647", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2352", "Green", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2353", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2354", "Black", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2355", "Orange", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assertions.assertThrows(ParkingLotException.class, () -> policeDepartment.getAllBMWVehicles());
    }

    @Test
    void givenPoliceDepartment_WhenSearchForHandicapVehicles_ShouldThrowException() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.SMALL, Vehicle.PersonType.HANDICAP, LocalDateTime.now(),
                "Honda", "5647", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2352", "Green", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.HANDICAP, LocalDateTime.now(),
                "Nissan", "2353", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.SMALL, Vehicle.PersonType.HANDICAP, LocalDateTime.now(),
                "Yamaha", "2354", "Black", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "2355", "Orange", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add(vehicle);
        expectedList.add(vehicle4);
        List actualList = policeDepartment.getAllHandicappedVehicles();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void givenPoliceDepartment_WhenFoundInvalidNumberPlates_ShouldReturnTrue() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.SMALL, Vehicle.PersonType.HANDICAP, LocalDateTime.now(),
                "Honda", "TS10ML10", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "TS10L10", "Green", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "T10ML10", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.SMALL, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Yamaha", "TS10ML10000", "Black", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "TS10ML1045", "Orange", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        List expectedList = new ArrayList();
        expectedList.add(vehicle2);
        expectedList.add(vehicle4);
        expectedList.add(vehicle3);
        List actualList = policeDepartment.vehicleNumberValidate();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void givenPoliceDepartment_WhenSearchForVehiclesParkedBefore30Minutes_ShouldThrowException() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        parkingLotSystem.setCapacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.SMALL, Vehicle.PersonType.HANDICAP, LocalDateTime.now(),
                "Honda", "TS10ML10", "Blue", "Naresh");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "TS10L10", "Green", "Naresh");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.LARGE, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "T10ML10", "Violet", "Naresh");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.SMALL, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Yamaha", "TS10ML10000", "Black", "Naresh");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL, LocalDateTime.now(),
                "Nissan", "TS10ML1045", "Orange", "Naresh");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assertions.assertThrows(ParkingLotException.class,
                () -> policeDepartment.getAllVehiclesParkedBefore30Minutes());
    }
}
