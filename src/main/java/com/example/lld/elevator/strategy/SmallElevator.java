package com.example.lld.elevator.strategy;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.enums.ElevatorState;
import com.example.lld.elevator.enums.ElevatorType;
import com.example.lld.elevator.interfaces.ElevatorI;

public class SmallElevator implements ElevatorI {

    @Override
    public Elevator createElevator(int maxFloor, int currentFloor) {
        return new Elevator(
                200,
                0,
                ElevatorType.SMALL,
                ElevatorState.IDLE,
                currentFloor,
                maxFloor);
    }
}