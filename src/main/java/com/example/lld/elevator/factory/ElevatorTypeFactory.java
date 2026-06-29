package com.example.lld.elevator.factory;

import com.example.lld.elevator.enums.ElevatorType;
import com.example.lld.elevator.interfaces.ElevatorI;
import com.example.lld.elevator.strategy.ExtraLargeElevator;
import com.example.lld.elevator.strategy.LargeElevator;
import com.example.lld.elevator.strategy.MediumElevator;
import com.example.lld.elevator.strategy.SmallElevator;

public class ElevatorTypeFactory {

    public static ElevatorI getStrategy(ElevatorType type) {
        switch (type) {
            case SMALL -> {
                return new SmallElevator();
            }
            case MEDIUM -> {
                return new MediumElevator();
            }
            case LARGE -> {
                return new LargeElevator();
            }
            case EXTRA_LARGE -> {
                return new ExtraLargeElevator();
            }
            default -> throw new IllegalArgumentException("Invalid type");
        }
    }
}