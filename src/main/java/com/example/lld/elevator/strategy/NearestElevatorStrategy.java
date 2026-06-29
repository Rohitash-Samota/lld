package com.example.lld.elevator.strategy;

import java.util.List;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.dto.Request;
import com.example.lld.elevator.enums.Direction;
import com.example.lld.elevator.enums.ElevatorState;
import com.example.lld.elevator.interfaces.DispatchStrategyI;

public class NearestElevatorStrategy implements DispatchStrategyI {
    @Override
    public Elevator findElevator(List<Elevator> elevators, Request request) {

        Elevator ans = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {

            if (!canServe(elevator, request)) {
                continue;
            }

            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());

            if (distance < minDistance) {
                minDistance = distance;
                ans = elevator;
            }
        }

        return ans;
    }

    private boolean canServe(Elevator elevator, Request request) {

        if (elevator.getElevatorState() == ElevatorState.IDLE) {
            return true;
        }

        if (elevator.getElevatorState() == ElevatorState.MOVING_UP && request.getDirection() == Direction.UP
                && request.getFloor() >= elevator.getCurrentFloor()) {
            return true;
        }

        if (elevator.getElevatorState() == ElevatorState.MOVING_DOWN && request.getDirection() == Direction.DOWN
                && request.getFloor() <= elevator.getCurrentFloor()) {
            return true;
        }

        return false;
    }
}
