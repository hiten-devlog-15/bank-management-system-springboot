package com.hiten.bank_management_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class CustomerResponse {
    private Long customerId;
    private String customerName;
    private String phoneNumber;
    private String email;
    private LocalDate createdAt;

    public CustomerResponse(Long customerId, String customerName, String phoneNumber, String email, LocalDate createdAt) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdAt = createdAt;
    }
}
