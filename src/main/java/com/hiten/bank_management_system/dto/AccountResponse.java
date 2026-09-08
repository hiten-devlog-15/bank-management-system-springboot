package com.hiten.bank_management_system.dto;

import com.hiten.bank_management_system.enums.AccountStatus;
import com.hiten.bank_management_system.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AccountResponse {
    private Long accountId;
    private Long customerId;
    private AccountType accountType;
    private double currentBalance;
    private AccountStatus accountStatus;
    private LocalDate createdAt;
}
