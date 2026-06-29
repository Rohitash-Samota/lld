package com.example.lld.elevator.exception;

public class ElevatorNotFoundException extends RuntimeException {
    public ElevatorNotFoundException() {
        super("No suitable elevator found.");
    }

    public ElevatorNotFoundException(String message) {
        super(message);
    }
}
