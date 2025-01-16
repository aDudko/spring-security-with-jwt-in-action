package com.dudko.example.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApplicationApiException extends RuntimeException {

    public ApplicationApiException(String message) {
        super(message);
    }

}
