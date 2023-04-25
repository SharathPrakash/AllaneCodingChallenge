package com.sp.coding.service;

import com.sp.coding.entity.Customer;
import com.sp.coding.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.customerService = new CustomerService(customerRepository);
    }

    @Test
    void testGetCustomerById() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John", "Doe");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(customerId);

        assertEquals(customer, result.get());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void testSaveCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John", "Doe");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.saveCustomer(customer);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "John", "Doe"));
        customers.add(new Customer(2L, "Jane", "Doe"));
        when(customerRepository.findAll()).thenReturn(customers);

        Optional<List<Customer>> result = customerService.getAllCustomer();

        assertEquals(customers, result.get());
        verify(customerRepository, times(1)).findAll();
    }
}