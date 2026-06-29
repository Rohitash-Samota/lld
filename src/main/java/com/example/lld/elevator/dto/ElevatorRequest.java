package com.example.lld.elevator.dto;

public class ElevatorRequest {
    private int maxFloor;
    private int currentFloor;

    public ElevatorRequest(int maxFloor, int currentFloor) {
        this.maxFloor = maxFloor;
        this.currentFloor = currentFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

}
