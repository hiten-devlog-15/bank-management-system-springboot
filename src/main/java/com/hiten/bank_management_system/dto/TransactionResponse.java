package com.hiten.bank_management_system.dto;

import com.hiten.bank_management_system.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TransactionResponse {
    private Long transactionId;
    private Long accountId;
    private TransactionType transactionType;
    private double amount;
    private LocalDate date;
}
