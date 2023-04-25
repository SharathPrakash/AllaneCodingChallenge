package com.sp.coding.controller;

import com.sp.coding.entity.Customer;
import com.sp.coding.entity.Vehicle;
import com.sp.coding.service.CustomerService;
import com.sp.coding.service.LeasingContractService;
import com.sp.coding.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ContractControllerTest {

    @Mock
    private LeasingContractService leasingContractService;

    @Mock
    private CustomerService customerService;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private ContractController contractController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customer));

        assertThat(contractController.getCustomer(customerId)).isEqualTo(customer);
    }

    @Test
    public void testGetCustomerNotFound() {
        Long customerId = 1L;
        when(customerService.getCustomerById(customerId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> contractController.getCustomer(customerId))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Customer not found")
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());
        when(customerService.getAllCustomer()).thenReturn(Optional.of(customers));

        assertThat(contractController.getAllCustomer()).isEqualTo(customers);
    }

    @Test
    public void testCreateOrUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

        assertThat(contractController.createOrUpdateCustomer(customer)).isEqualTo(customer);
    }

    @Test
    public void testGetVehicle() {
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        when(vehicleService.getVehicleById(vehicleId)).thenReturn(Optional.of(vehicle));

        assertThat(contractController.getVehicle(vehicleId)).isEqualTo(vehicle);
    }

    @Test
    public void testGetVehicleNotFound() {
        Long vehicleId = 1L;
        when(vehicleService.getVehicleById(vehicleId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> contractController.getVehicle(vehicleId))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Vehicle not found")
                .extracting("status")
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCreateOrUpdateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        when(vehicleService.saveVehicle(any(Vehicle.class))).thenReturn(vehicle);

        assertThat(contractController.createOrUpdateVehicle(vehicle)).isEqualTo(vehicle);
    }
}