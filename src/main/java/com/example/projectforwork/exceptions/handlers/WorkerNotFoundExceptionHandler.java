package com.example.projectforwork.exceptions.handlers;

import com.example.projectforwork.exceptions.WorkerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WorkerNotFoundExceptionHandler {
    @ExceptionHandler(WorkerNotFoundException.class)
    public ResponseEntity<String> workerNotFoundExceptionHandler(WorkerNotFoundException workerNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(workerNotFoundException.getMessage());
    }
}
