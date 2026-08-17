package com.hiten.bank_management_system.service;

import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.enums.AccountStatus;
import com.hiten.bank_management_system.enums.AccountType;
import com.hiten.bank_management_system.repository.AccountRepository;
import com.hiten.bank_management_system.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository){
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account createAccount(Long customerId, AccountType accountType, double initialDeposit){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        Account account = new Account(customer, accountType, initialDeposit, AccountStatus.ACTIVE, LocalDate.now());
        accountRepository.save(account);
        return account;
    }
}
