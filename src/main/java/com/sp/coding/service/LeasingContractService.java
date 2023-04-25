package com.sp.coding.service;

import com.sp.coding.entity.LeasingContract;
import com.sp.coding.entity.Vehicle;
import com.sp.coding.model.ContractOverview;
import com.sp.coding.repository.LeasingContractRepository;
import com.sp.coding.util.PriceCalculator;
import com.sp.coding.util.RandomNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeasingContractService {
    private final LeasingContractRepository leasingContractRepository;

    public LeasingContractService(LeasingContractRepository contractRepository) {
        this.leasingContractRepository = contractRepository;
    }

    public ContractOverview mapToContractOverview(LeasingContract contract) {
        ContractOverview overview = new ContractOverview();
        overview.setContractNo(contract.getContractNumber());
        overview.setCustomerName(contract.getCustomer().getFirstName() + " " + contract.getCustomer().getLastName());
        Vehicle vehicle = contract.getVehicle();
        overview.setVehicleSummary(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getModelYear() + ") ");
        overview.setVin(vehicle.getVin() != null && !vehicle.getVin().isBlank() ? vehicle.getVin() : "-");
        overview.setMonthlyRate(contract.getMonthlyRate());
        overview.setVehiclePrice(vehicle.getPrice());
        overview.setContractDetailsLink("/leasingContracts/" + contract.getId());

        return overview;
    }

    public List<LeasingContract> getAllContracts() {
        return leasingContractRepository.findAll();
    }

    public LeasingContract saveLeasingContract(LeasingContract leasingContract) {
        leasingContract.setContractNumber(RandomNumberGenerator.generateContractRandomNumber());
        leasingContract.setMonthlyRate(PriceCalculator.calculateMonthlyRate(leasingContract.getVehicle().getPrice()));
        return leasingContractRepository.save(leasingContract);
    }
}