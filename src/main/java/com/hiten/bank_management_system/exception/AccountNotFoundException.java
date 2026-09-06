package com.hiten.bank_management_system.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message){
        super(message);
    }
}
