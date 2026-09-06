package com.hiten.bank_management_system.exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message){
        super(message);
    }
}
