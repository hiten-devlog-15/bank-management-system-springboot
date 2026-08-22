package com.hiten.bank_management_system.service;

import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.enums.AccountStatus;
import com.hiten.bank_management_system.enums.AccountType;
import com.hiten.bank_management_system.enums.TransactionType;
import com.hiten.bank_management_system.repository.AccountRepository;
import com.hiten.bank_management_system.repository.CustomerRepository;
import com.hiten.bank_management_system.repository.TransactionRepository;
import com.hiten.bank_management_system.validator.Validator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;
    private final Validator validator;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository,
                          TransactionService transactionService, Validator validator){
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionService = transactionService;
        this.validator = validator;
    }

    public Account createAccount(Long customerId, AccountType accountType, double initialDeposit){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        if(!validator.isInitialDepositValid(initialDeposit, accountType)){
            throw new RuntimeException("Invalid Initial Deposit");
        }
        Account account = new Account(customer, accountType, initialDeposit, AccountStatus.ACTIVE, LocalDate.now());
        accountRepository.save(account);
        return account;
    }

    public void deposit(Long accountId, double amount){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if(!validator.isAccountActive(account) || !validator.isAmountValid(amount)){
            throw new RuntimeException("Invalid deposit");
        }
        account.deposit(amount);
        accountRepository.save(account);
        transactionService.createTransaction(account, TransactionType.DEPOSIT, amount);
    }

    public void withdraw(Long accountId, double amount, String password){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if(!validator.isAccountActive(account) || !validator.isAmountValid(amount)
                || !validator.hasSufficientBalance(account, amount)
                || !validator.verifyPassword(account.getCustomer().getCustomerId(), password)){
            throw new RuntimeException("Invalid withdraw");
        }
        account.withdraw(amount);
        accountRepository.save(account);
        transactionService.createTransaction(account, TransactionType.WITHDRAW, amount);
    }

    public void transfer(Long senderAccountId, Long receiverAccountId, double amount, String password){
        Account senderAccount = accountRepository.findById(senderAccountId).orElseThrow(() ->
                new RuntimeException("Sender Account not found"));
        Account receiverAccount = accountRepository.findById(receiverAccountId).orElseThrow(() ->
                new RuntimeException("Receiver Account not found"));
        if (senderAccountId.equals(receiverAccountId)) {
            throw new RuntimeException("Sender and receiver accounts cannot be the same");
        }
        if(!validator.isAccountActive(senderAccount) || !validator.isAccountActive(receiverAccount) || !validator.isAmountValid(amount)
                || !validator.hasSufficientBalance(senderAccount, amount)
                || !validator.verifyPassword(senderAccount.getCustomer().getCustomerId(), password)){
            throw new RuntimeException("Invalid Transfer");
        }
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
        transactionService.createTransaction(senderAccount, TransactionType.TRANSFER_OUT, amount);
        transactionService.createTransaction(receiverAccount, TransactionType.TRANSFER_IN, amount);
    }
}
