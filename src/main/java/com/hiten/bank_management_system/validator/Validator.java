package com.hiten.bank_management_system.validator;

import com.hiten.bank_management_system.entity.Account;
import com.hiten.bank_management_system.entity.Customer;
import com.hiten.bank_management_system.enums.AccountType;
import com.hiten.bank_management_system.repository.AccountRepository;
import com.hiten.bank_management_system.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class Validator {


    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    public Validator(CustomerRepository customerRepository, AccountRepository accountRepository){
        this.customerRepository=customerRepository;
        this.accountRepository=accountRepository;
    }

    public boolean isEmailValid(String email){
        if(email.endsWith("@gmail.com")){
            return true;
        }
        return false;
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        if(phoneNumber.length() == 10){
            return true;
        }
        return false;
    }

    public boolean isInitialDepositValid(double initialDeposit, AccountType accountType){
        if(accountType.equals(AccountType.SAVINGS) && initialDeposit>=2000 ){
            return true;
        } else if (accountType.equals(AccountType.CURRENT) && initialDeposit>=5000) {
            return true;
        }
        return false;
    }

    public boolean isAmountValid(double amount){
        if(amount > 0){
            return true;
        }
        return false;
    }

    public boolean isAccountValid(Long accountId){
        if(accountRepository.existsById(accountId)){
            return true;
        }
        return false;
    }

    public boolean verifyPassword(Long customerId, String password){
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        if(password.equals(customer.getPassword())){
            return true;
        }
        return false;
    }

    public boolean hasSufficientBalance(Long accountId, double amount){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getCurrentBalance() >= amount){
            return true;
        }
        return false;
    }

}
