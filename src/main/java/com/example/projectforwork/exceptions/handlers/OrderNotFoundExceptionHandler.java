package com.example.projectforwork.exceptions.handlers;

import com.example.projectforwork.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderNotFoundExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFoundExceptionHandler(OrderNotFoundException orderNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderNotFoundException.getMessage());
    }
}
