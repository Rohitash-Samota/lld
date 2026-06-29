package com.example.lld.elevator.exception;

public class ServiceDownException extends RuntimeException {

    public ServiceDownException() {
        super("Elevator service is currently unavailable.");
    }

    public ServiceDownException(String message) {
        super(message);
    }
}