package com.hiten.bank_management_system;

import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CustomerTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCustomerRepository(){
        List<Customer> customerList = customerRepository.findAll();
        System.out.println(customerList);
    }
}
