package com.example.lld.elevator.strategy;

import java.util.List;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.dto.Request;
import com.example.lld.elevator.interfaces.DispatchStrategyI;

public class NearestElevatorAvailable implements DispatchStrategyI {

    @Override
    public Elevator findElevator(List<Elevator> elevators, Request request) {

        Elevator ans = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int distance = Math.abs(elevator.getCurrentFloor() - request.getFloor());

            if (distance < minDistance) {
                minDistance = distance;
                ans = elevator;
            }
        }

        return ans;
    }
}
