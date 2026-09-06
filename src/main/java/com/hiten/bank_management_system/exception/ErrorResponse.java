package com.hiten.bank_management_system.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    public ErrorResponse(int status, String message){
        this.status=status;
        this.message=message;
    }
}
