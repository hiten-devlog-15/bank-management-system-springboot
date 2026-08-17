package com.hiten.bank_management_system.service;

import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(String name, String phoneNumber, String email, String password){
        LocalDate createdAt = LocalDate.now();
        Customer customer = new Customer(name, phoneNumber, email, password, createdAt);
        customerRepository.save(customer);
        return customer;
    }
}
