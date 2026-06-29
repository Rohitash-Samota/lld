package com.example.lld.elevator.interfaces;

import java.util.List;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.dto.Request;

public interface DispatchStrategyI {

    Elevator findElevator(List<Elevator> elevators, Request request);
}