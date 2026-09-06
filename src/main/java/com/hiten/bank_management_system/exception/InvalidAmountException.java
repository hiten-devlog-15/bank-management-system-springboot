package com.hiten.bank_management_system.exception;

public class InvalidAmountException extends RuntimeException{

    public InvalidAmountException(String message){
        super(message);
    }
}
