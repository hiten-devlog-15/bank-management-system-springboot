package com.hiten.bank_management_system.service;

import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.enums.AccountStatus;
import com.hiten.bank_management_system.enums.AccountType;
import com.hiten.bank_management_system.enums.TransactionType;
import com.hiten.bank_management_system.exception.*;
import com.hiten.bank_management_system.repository.AccountRepository;
import com.hiten.bank_management_system.repository.CustomerRepository;
import com.hiten.bank_management_system.validator.Validator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

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
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        if(!validator.isInitialDepositValid(initialDeposit, accountType)){
            throw new InvalidInitialDepositException("Invalid Initial Deposit");
        }
        Account account = new Account(customer, accountType, initialDeposit, AccountStatus.ACTIVE, LocalDate.now());
        accountRepository.save(account);
        return account;
    }

    public void deposit(Long accountId, double amount){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if(!validator.isAccountActive(account)){
            throw new AccountNotActiveException("Inactive account");
        }
        if(!validator.isAmountValid(amount)){
            throw new InvalidAmountException("Invalid Amount");
        }
        account.deposit(amount);
        accountRepository.save(account);
        transactionService.createTransaction(account, TransactionType.DEPOSIT, amount);
    }

    public void withdraw(Long accountId, double amount, String password){
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new AccountNotFoundException("Account not found"));
        if (!validator.isAccountActive(account)) {
            throw new AccountNotActiveException("Inactive Account");
        }

        if (!validator.isAmountValid(amount)) {
            throw new InvalidAmountException("Invalid amount");
        }

        if (!validator.hasSufficientBalance(account, amount)) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        if (!validator.verifyPassword(
                account.getCustomer().getCustomerId(), password)) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        account.withdraw(amount);
        accountRepository.save(account);
        transactionService.createTransaction(account, TransactionType.WITHDRAW, amount);
    }

    @Transactional
    public void transfer(Long senderAccountId, Long receiverAccountId, double amount, String password){
        Account senderAccount = accountRepository.findById(senderAccountId).orElseThrow(() ->
                new AccountNotFoundException("Sender Account not found"));
        Account receiverAccount = accountRepository.findById(receiverAccountId).orElseThrow(() ->
                new AccountNotFoundException("Receiver Account not found"));
        if (senderAccountId.equals(receiverAccountId)) {
            throw new SameAccountTransferException("Sender and receiver accounts cannot be the same");
        }
        if(!validator.isAccountActive(senderAccount)){
            throw new AccountNotActiveException("Sender account inactive");
        }
        if(!validator.isAccountActive(receiverAccount)){
            throw new AccountNotActiveException("Receiver account inactive");
        }
        if(!validator.isAmountValid(amount)){
            throw new InvalidAmountException("Invalid amount");
        }
        if(!validator.hasSufficientBalance(senderAccount, amount)){
            throw new InsufficientBalanceException("Insufficient balance");
        }
        if(!validator.verifyPassword(senderAccount.getCustomer().getCustomerId(), password)){
            throw new IncorrectPasswordException("Incorrect password");
        }
        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
        transactionService.createTransaction(senderAccount, TransactionType.TRANSFER_OUT, amount);
        transactionService.createTransaction(receiverAccount, TransactionType.TRANSFER_IN, amount);
    }

    public void closeAccount(Long accountId, String password){
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new AccountNotFoundException("Account not found"));
        if(!validator.isAccountActive(account)){
            throw new AccountNotActiveException("Inactive Account");
        }
        if(!validator.verifyPassword(account.getCustomer().getCustomerId(), password)){
            throw new IncorrectPasswordException("Incorrect Password");
        }
        if(account.getCurrentBalance() > 0){
            throw new NonZeroBalanceException("Balance greater than zero cannot close account");
        }
        account.closeAccount();
        accountRepository.save(account);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public List<Account> getAllAccounts(Long customerId){
        if (!validator.existsCustomer(customerId)){
            throw new CustomerNotFoundException("Customer not found");
        }
        return accountRepository.findByCustomer_CustomerId(customerId);
    }
}
