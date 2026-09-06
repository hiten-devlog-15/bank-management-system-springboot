package com.hiten.bank_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAccountNotFound(AccountNotFoundException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerNotFound(CustomerNotFoundException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleInsufficientBalance(InsufficientBalanceException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(InvalidAmountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleInvalidAmount(InvalidAmountException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleIncorrectPassword(IncorrectPasswordException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(AccountNotActiveException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAccountNotActive(AccountNotActiveException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(InvalidInitialDepositException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAccountNotActive(InvalidInitialDepositException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(NonZeroBalanceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAccountNotActive(NonZeroBalanceException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(SameAccountTransferException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAccountNotActive(SameAccountTransferException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
