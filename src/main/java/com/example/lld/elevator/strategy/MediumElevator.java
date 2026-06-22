package com.example.lld.elevator.strategy;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.enums.ElevatorState;
import com.example.lld.elevator.enums.ElevatorType;

public class MediumElevator implements ElevatorI {

    @Override
    public Elevator createElevator(int maxFloor, int currentFloor) {
        return new Elevator(
                500,
                0,
                ElevatorType.MEDIUM,
                ElevatorState.IDLE,
                currentFloor,
                maxFloor);
    }
}