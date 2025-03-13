package com.example.projectforwork.exceptions;

public class WorkerNotFoundException extends RuntimeException {
    public WorkerNotFoundException() {
        super("this worker doesnt exist");
    }
}
