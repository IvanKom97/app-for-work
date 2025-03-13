package com.example.projectforwork.exceptions.handlers;

import com.example.projectforwork.exceptions.OutOfMaxPagesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OutOfMaxPagesExceptionHandler {
    @ExceptionHandler(OutOfMaxPagesException.class)
    public ResponseEntity<String> outOfMaxPagesException(OutOfMaxPagesException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
