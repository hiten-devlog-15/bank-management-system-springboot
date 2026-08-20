package com.hiten.bank_management_system.service;

import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.entity.Transaction;
import com.hiten.bank_management_system.enums.TransactionType;
import com.hiten.bank_management_system.repository.AccountRepository;
import com.hiten.bank_management_system.repository.CustomerRepository;
import com.hiten.bank_management_system.repository.TransactionRepository;
import com.hiten.bank_management_system.validator.Validator;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final Validator validator;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, Validator validator){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.validator = validator;
    }

    public Transaction createTransaction(Account account, TransactionType transactionType, double amount){
        LocalDate date = LocalDate.now();
        Transaction transaction = new Transaction(account, transactionType, amount, date, account.getCurrentBalance());
        transactionRepository.save(transaction);
        return transaction;
    }

}
