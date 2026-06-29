package com.example.lld.elevator.dto;

import com.example.lld.elevator.enums.Direction;

public class Request {

    private int floor;
    private Direction direction;
    private boolean hallCall;

    public Request(int floor, Direction direction, boolean hallCall) {
        this.floor = floor;
        this.direction = direction;
        this.hallCall = hallCall;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isHallCall() {
        return hallCall;
    }
}