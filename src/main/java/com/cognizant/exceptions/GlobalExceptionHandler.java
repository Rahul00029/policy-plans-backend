package com.cognizant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DuplicateSubscriptionException.class})
    public ResponseEntity<Object>handleDuplicateSubscriptionEception(DuplicateSubscriptionException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({PolicyTypeNotFoundException.class})
    public ResponseEntity<Object>handlePolicyNotFoundException(PolicyTypeNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object>handleRuntimeException(RuntimeException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
