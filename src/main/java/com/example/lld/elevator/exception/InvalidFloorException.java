package com.example.lld.elevator.exception;

public class InvalidFloorException extends RuntimeException {
    public InvalidFloorException(String message) {
        super(message);
    }
}
