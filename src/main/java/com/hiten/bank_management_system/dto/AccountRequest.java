package com.hiten.bank_management_system.dto;

import com.hiten.bank_management_system.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountRequest {
    private Long customerId;
    private AccountType accountType;
    private double initialDeposit;
}
