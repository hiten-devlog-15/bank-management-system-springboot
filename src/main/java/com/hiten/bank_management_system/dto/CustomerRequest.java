package com.hiten.bank_management_system.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequest {
    private String customerName;
    private String phoneNumber;
    private String email;
    private String password;
}
