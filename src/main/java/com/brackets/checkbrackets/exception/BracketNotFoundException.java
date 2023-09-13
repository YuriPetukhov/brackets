package com.brackets.checkbrackets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BracketNotFoundException extends RuntimeException{
    public BracketNotFoundException(String message) {
        super(message);
    }
}
