package com.hiten.bank_management_system.mapper;

import com.hiten.bank_management_system.dto.CustomerResponse;
import com.hiten.bank_management_system.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(customer.getCustomerId(), customer.getCustomerName(), customer.getPhoneNumber(),
                customer.getEmail(), customer.getCreatedAt()
        );
    }
}
