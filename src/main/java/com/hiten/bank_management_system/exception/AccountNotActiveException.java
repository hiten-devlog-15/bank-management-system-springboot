package com.hiten.bank_management_system.exception;

public class AccountNotActiveException extends RuntimeException{

    public AccountNotActiveException(String message){
        super(message);
    }
}
