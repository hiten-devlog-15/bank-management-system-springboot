package com.hiten.bank_management_system.controller;

import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable Long customerId){
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public Customer registerCustomer(@RequestBody Customer customer){
        return customerService.registerCustomer(customer.getCustomerName(), customer.getPhoneNumber(),
                customer.getEmail(), customer.getPassword());
    }
}
