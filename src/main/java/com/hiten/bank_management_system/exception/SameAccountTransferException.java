package com.hiten.bank_management_system.exception;

public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException(String message) {
        super(message);
    }
}
