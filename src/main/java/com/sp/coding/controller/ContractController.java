package com.sp.coding.controller;

import com.sp.coding.entity.Customer;
import com.sp.coding.entity.LeasingContract;
import com.sp.coding.entity.Vehicle;
import com.sp.coding.model.ContractOverview;
import com.sp.coding.model.ContractRequest;
import com.sp.coding.service.CustomerService;
import com.sp.coding.service.LeasingContractService;
import com.sp.coding.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ContractController {

    private final LeasingContractService leasingContractService;
    private final CustomerService customerService;
    private final VehicleService vehicleService;

    public ContractController(LeasingContractService contractService, CustomerService customerService, VehicleService vehicleService) {
        this.leasingContractService = contractService;
        this.customerService = customerService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer().get();
    }

    @PostMapping("/customers")
    public Customer createOrUpdateCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/vehicles/{id}")
    public Vehicle getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    @PostMapping("/vehicles")
    public Vehicle createOrUpdateVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PostMapping("/contracts")
    public LeasingContract createContract(@RequestBody ContractRequest contractRequest) {
        Long customerId = Long.valueOf(contractRequest.getCustomerId());
        Long vehicleId = Long.valueOf(contractRequest.getVehicleId());

        Customer customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Vehicle vehicle = vehicleService.getVehicleById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        LeasingContract leasingContract = new LeasingContract();

        leasingContract.setCustomer(customer);
        leasingContract.setVehicle(vehicle);

        return Optional.of(leasingContract)
                .map(leasingContractService::saveLeasingContract)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save leasingContract"));
    }

    @GetMapping("/contracts")
    public List<ContractOverview> getContractOverview() {
        return leasingContractService.getAllContracts().stream()
                .map(leasingContractService::mapToContractOverview)
                .collect(Collectors.toList());
    }
}
