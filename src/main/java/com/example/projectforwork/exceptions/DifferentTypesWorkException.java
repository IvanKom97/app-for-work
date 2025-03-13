package com.example.projectforwork.exceptions;

import com.example.projectforwork.enums.TypesRepairs;

public class DifferentTypesWorkException extends RuntimeException{
    public DifferentTypesWorkException(TypesRepairs typesRepairs) {
        super("Selected type of work doesnt belong to  " + typesRepairs.name());
    }
}
