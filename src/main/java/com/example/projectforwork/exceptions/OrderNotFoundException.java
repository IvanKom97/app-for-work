package com.example.projectforwork.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("this order doesnt exist");
    }
}
