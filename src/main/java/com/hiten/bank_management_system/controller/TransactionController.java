package com.hiten.bank_management_system.controller;

import com.hiten.bank_management_system.dto.TransactionResponse;
import com.hiten.bank_management_system.entity.Transaction;
import com.hiten.bank_management_system.mapper.TransactionMapper;
import com.hiten.bank_management_system.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody Transaction transaction){
         transaction = transactionService.createTransaction(transaction.getAccount(), transaction.getTransactionType(),
                 transaction.getAmount());
         return transactionMapper.toResponse(transaction);
    }

    @GetMapping("/{accountId}")
    public List<TransactionResponse> getTransactions(@PathVariable Long accountId){
        List<Transaction> transactionList = transactionService.getTransactions(accountId);
        return transactionMapper.toResponse(transactionList);
    }
}
