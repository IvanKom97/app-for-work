package com.example.projectforwork.exceptions.handlers;

import com.example.projectforwork.exceptions.ForrbidenByCompanyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ForrbidenByCompanyExceptionHandler {

    @ExceptionHandler(ForrbidenByCompanyException.class)
    public ResponseEntity<String> forrbidenExceptionHandler(ForrbidenByCompanyException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
