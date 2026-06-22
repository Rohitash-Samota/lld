package com.example.lld.elevator.dto;

import com.example.lld.elevator.enums.ElevatorState;
import com.example.lld.elevator.enums.ElevatorType;

public class Elevator {
    private int capacity;
    private int currentWeight;
    private ElevatorType elevatorType;
    private ElevatorState elevatorState;
    private int currentFloor;
    private int maxFloor;
    private boolean isOpenDoor;

    public Elevator(int capacity, int currentWeight, ElevatorType elevatorType, ElevatorState elevatorState,
            int currentFloor, int maxFloor) {
        this.capacity = capacity;
        this.currentWeight = currentWeight;
        this.elevatorType = elevatorType;
        this.elevatorState = elevatorState;
        this.currentFloor = currentFloor;
        this.maxFloor = maxFloor;
        this.isOpenDoor = false;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public ElevatorType getElevatorType() {
        return elevatorType;
    }

    public void setElevatorType(ElevatorType elevatorType) {
        this.elevatorType = elevatorType;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public boolean isOpenDoor() {
        return isOpenDoor;
    }

    public void setOpenDoor(boolean isOpenDoor) {
        this.isOpenDoor = isOpenDoor;
    }

}
