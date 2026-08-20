package com.hiten.bank_management_system.service;

import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.repository.CustomerRepository;
import com.hiten.bank_management_system.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Validator validator;

    public CustomerService(CustomerRepository customerRepository, Validator validator) {
        this.customerRepository = customerRepository;
        this.validator = validator;
    }

    public Customer registerCustomer(String name, String phoneNumber,
                                     String email, String password) {
        LocalDate createdAt = LocalDate.now();
        if (!validator.isPhoneNumberValid(phoneNumber)) {
            throw new RuntimeException("Invalid phone number");
        }
        if (!validator.isEmailValid(email)) {
            throw new RuntimeException("Invalid email");
        }
        Customer customer = new Customer(
                name, phoneNumber, email, password, createdAt
        );
        customerRepository.save(customer);
        return customer;
    }
}
