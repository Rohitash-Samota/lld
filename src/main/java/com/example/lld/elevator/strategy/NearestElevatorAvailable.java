package com.example.lld.elevator.strategy;

import java.util.List;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.dto.Request;
import com.example.lld.elevator.interfaces.DispatchStrategyI;
import com.example.lld.elevator.repo.ElevatorRepo;

public class NearestElevatorAvailable implements DispatchStrategyI {
    private final ElevatorRepo elevatorRepo;

    public NearestElevatorAvailable(ElevatorRepo elevatorRepo) {
        this.elevatorRepo = elevatorRepo;
    }

    @Override
    public Elevator findElevator(Request request) {
        List<Elevator> elevators = elevatorRepo.getAllElevators();

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
