package com.brackets.checkbrackets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BracketAllreadyExistException extends RuntimeException {
    public BracketAllreadyExistException(String message) {
        super(message);
    }
}
