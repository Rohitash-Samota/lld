package com.example.lld.elevator.strategy;

import com.example.lld.elevator.dto.Elevator;

public interface ElevatorI {
    Elevator createElevator(int maxFloor, int currentFloor);
}