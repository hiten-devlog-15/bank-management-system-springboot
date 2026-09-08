package com.hiten.bank_management_system.controller;

import com.hiten.bank_management_system.dto.CustomerRequest;
import com.hiten.bank_management_system.dto.CustomerResponse;
import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.mapper.CustomerMapper;
import com.hiten.bank_management_system.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper){
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable Long customerId){
        Customer customer = customerService.getCustomer(customerId);
        return customerMapper.toResponse(customer);
    }

    @PostMapping
    public CustomerResponse registerCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer = customerService.registerCustomer(customerRequest.getCustomerName(), customerRequest.getPhoneNumber(),
                customerRequest.getEmail(), customerRequest.getPassword());
        return customerMapper.toResponse(customer);
    }
}
