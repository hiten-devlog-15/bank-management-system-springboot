package com.hiten.bank_management_system.mapper;

import com.hiten.bank_management_system.dto.TransactionResponse;
import com.hiten.bank_management_system.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionMapper {

    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getAccount().getAccountId(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getDate()
        );
    }
    public List<TransactionResponse> toResponse(List<Transaction> transactionList) {

        return transactionList.stream()
                .map(this::toResponse)
                .toList();
    }
}
