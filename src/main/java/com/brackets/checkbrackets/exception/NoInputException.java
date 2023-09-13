package com.brackets.checkbrackets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoInputException extends RuntimeException{
    public NoInputException(String message) {
        super(message);
    }
}