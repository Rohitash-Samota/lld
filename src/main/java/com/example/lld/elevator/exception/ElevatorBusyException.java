package com.example.lld.elevator.exception;

public class ElevatorBusyException extends RuntimeException {
    public ElevatorBusyException() {
        super("All Elevator Busy");
    }

    public ElevatorBusyException(String message) {
        super(message);
    }
}
