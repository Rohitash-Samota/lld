package com.example.lld.elevator.interfaces;

import com.example.lld.elevator.dto.Elevator;

public interface ElevatorI {
    Elevator createElevator(int maxFloor, int currentFloor);
}