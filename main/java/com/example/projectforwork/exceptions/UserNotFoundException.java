package com.example.projectforwork.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("This user doesnt exist");
    }
}
