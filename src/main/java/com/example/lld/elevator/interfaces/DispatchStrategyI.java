package com.example.lld.elevator.interfaces;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.dto.Request;

public interface DispatchStrategyI {

    Elevator findElevator(Request request);
}