package com.sp.coding.service;

import com.sp.coding.entity.Customer;
import com.sp.coding.entity.LeasingContract;
import com.sp.coding.entity.Vehicle;
import com.sp.coding.model.ContractOverview;
import com.sp.coding.repository.LeasingContractRepository;
import com.sp.coding.util.PriceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LeasingContractServiceTest {

    private LeasingContractService leasingContractService;
    private LeasingContractRepository leasingContractRepositoryMock;

    @BeforeEach
    public void setUp() {
        leasingContractRepositoryMock = mock(LeasingContractRepository.class);
        leasingContractService = new LeasingContractService(leasingContractRepositoryMock);
    }

    @Test
    public void testMapToContractOverview() {
        // create a sample leasing contract
        Customer customer = new Customer(1L, "john", "Doe");
        Vehicle vehicle = new Vehicle("Ford", "Mustang", 2022, Double.parseDouble("45000"));

        LeasingContract contract = new LeasingContract(customer, vehicle);

        // call the method under test
        ContractOverview overview = leasingContractService.mapToContractOverview(contract);

        // assert the results
        assertEquals(contract.getContractNumber(), overview.getContractNo());
        assertEquals(customer.getFirstName() + " " + customer.getLastName(), overview.getCustomerName());
        assertEquals(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getModelYear() + ") ", overview.getVehicleSummary());
        assertEquals(contract.getMonthlyRate(), overview.getMonthlyRate());
        assertEquals(vehicle.getPrice(), overview.getVehiclePrice());
        assertEquals("/leasingContracts/" + contract.getId(), overview.getContractDetailsLink());
    }

    @Test
    public void testGetAllContracts() {
        // create some sample leasing contracts
        LeasingContract contract1 = new LeasingContract();
        LeasingContract contract2 = new LeasingContract();
        List<LeasingContract> contracts = new ArrayList<>();
        contracts.add(contract1);
        contracts.add(contract2);

        // mock the repository to return the sample contracts
        when(leasingContractRepositoryMock.findAll()).thenReturn(contracts);

        // call the method under test
        List<LeasingContract> result = leasingContractService.getAllContracts();

        // assert the results
        assertEquals(2, result.size());
        assertEquals(contract1, result.get(0));
        assertEquals(contract2, result.get(1));
    }

    @Test
    public void testSaveLeasingContract() {
        // create a sample leasing contract
        Customer customer = new Customer(1L, "john", "Doe");
        Vehicle vehicle = new Vehicle("Ford", "Mustang", 2022, Double.parseDouble("45000"));
        LeasingContract contract = new LeasingContract(customer, vehicle);

        // mock the repository to return the saved contract
        when(leasingContractRepositoryMock.save(contract)).thenReturn(contract);

        // call the method under test
        LeasingContract result = leasingContractService.saveLeasingContract(contract);

        // assert the results
        verify(leasingContractRepositoryMock, times(1)).save(contract);
        assertEquals(contract.getContractNumber(), result.getContractNumber());
        assertEquals(PriceCalculator.calculateMonthlyRate(vehicle.getPrice()), result.getMonthlyRate());
    }
}