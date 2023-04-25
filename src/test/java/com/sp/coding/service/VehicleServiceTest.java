package com.sp.coding.service;

import com.sp.coding.entity.Vehicle;
import com.sp.coding.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class VehicleServiceTest {

    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleService(vehicleRepository);
    }

    @Test
    void getVehicleById_shouldReturnVehicleWithMatchingId() {
        // Arrange
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        // Act
        Optional<Vehicle> result = vehicleService.getVehicleById(vehicleId);

        // Assert
        assertEquals(vehicle, result.get());
    }

    @Test
    void saveVehicle_shouldReturnSavedVehicle() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        // Act
        Vehicle result = vehicleService.saveVehicle(vehicle);

        // Assert
        assertEquals(vehicle, result);
    }
}