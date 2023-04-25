package com.sp.coding.service;

import com.sp.coding.entity.Customer;
import com.sp.coding.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<List<Customer>> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.isEmpty() ? Optional.empty() : Optional.of(customers);
    }
}