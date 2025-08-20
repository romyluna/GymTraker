package com.proyecto.gymtracker.exception;

public class ResourceNotFoundException extends RuntimeException  {

    public ResourceNotFoundException(String message) {

        super(message);
    }
}
