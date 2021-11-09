package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    Object vehicle;
    ParkingLotSystem parkingLotSystem;

    @BeforeEach
    void setUp() {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = parkingLotSystem.park(new Object());
        Assertions.assertTrue(isParked);
    }
}
