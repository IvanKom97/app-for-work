package com.example.projectforwork.exceptions.handlers;

import com.example.projectforwork.exceptions.PageCantBeZeroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PageCantBeZeroExceptionHandler {
    @ExceptionHandler(PageCantBeZeroException.class)
    public ResponseEntity<String> pageCantBeZero(PageCantBeZeroException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
