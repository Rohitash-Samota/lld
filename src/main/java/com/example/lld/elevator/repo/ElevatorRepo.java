package com.example.lld.elevator.repo;

import java.util.ArrayList;
import java.util.List;

import com.example.lld.elevator.dto.Elevator;

public class ElevatorRepo {
    private final List<Elevator> elevators = new ArrayList<>();

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public List<Elevator> getAllElevators() {
        return elevators;
    }

    public Elevator getElevator(int index) {
        return elevators.get(index);
    }
}
