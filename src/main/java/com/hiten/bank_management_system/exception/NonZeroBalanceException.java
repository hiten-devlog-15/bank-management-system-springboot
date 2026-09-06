package com.hiten.bank_management_system.exception;

public class NonZeroBalanceException extends RuntimeException {
    public NonZeroBalanceException(String message) {
        super(message);
    }
}
