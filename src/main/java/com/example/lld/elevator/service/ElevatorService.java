package com.example.lld.elevator.service;

import java.util.List;

import com.example.lld.elevator.dto.Elevator;
import com.example.lld.elevator.dto.ElevatorRequest;
import com.example.lld.elevator.dto.Request;
import com.example.lld.elevator.enums.ElevatorType;
import com.example.lld.elevator.exception.ElevatorNotFoundException;
import com.example.lld.elevator.exception.InvalidFloorException;
import com.example.lld.elevator.exception.InvalidRequestException;
import com.example.lld.elevator.exception.ServiceDownException;
import com.example.lld.elevator.factory.ElevatorTypeFactory;
import com.example.lld.elevator.interfaces.DispatchStrategyI;
import com.example.lld.elevator.interfaces.ElevatorI;
import com.example.lld.elevator.repo.ElevatorRepo;

public class ElevatorService {

    private final ElevatorRepo elevatorRepo;
    private final ElevatorTypeFactory elevatorTypeFactory;

    private final int MAX_FLOOR = 20;

    public ElevatorService(ElevatorRepo elevatorRepo, ElevatorTypeFactory elevatorTypeFactory) {
        this.elevatorRepo = elevatorRepo;
        this.elevatorTypeFactory = elevatorTypeFactory;
    }

    public Elevator getOptimizeElevator(Request request, DispatchStrategyI dispatchStrategyI) {
        try {

            if (request.getFloor() < 0 || request.getFloor() > MAX_FLOOR) {
                throw new InvalidFloorException("Invalid floor number.");
            }

            List<Elevator> elevators = elevatorRepo.getAllElevators();
            if (elevators.isEmpty()) {
                throw new ElevatorNotFoundException("No elevators are available.");
            }

            Elevator elevator = dispatchStrategyI.findElevator(elevators, request);
            if (elevator == null) {
                throw new ElevatorNotFoundException("No suitable elevator found for the request.");
            }
            return elevator;
        } catch (ServiceDownException e) {
            throw new ServiceDownException(e.getMessage());
        } catch (Exception e) {
            return null;
        }
    }

    public Elevator createElevator(ElevatorRequest elevatorRequest,
            ElevatorType elevatorType) {

        if (elevatorRequest == null) {
            throw new InvalidRequestException("Elevator request cannot be null.");
        }

        if (elevatorType == null) {
            throw new InvalidRequestException("Elevator type cannot be null.");
        }

        if (elevatorRequest.getCurrentFloor() < 0
                || elevatorRequest.getCurrentFloor() > MAX_FLOOR) {
            throw new InvalidFloorException("Invalid current floor.");
        }

        if (elevatorRequest.getMaxFloor() <= 0
                || elevatorRequest.getMaxFloor() > MAX_FLOOR) {
            throw new InvalidFloorException("Invalid maximum floor.");
        }

        try {
            ElevatorI elevatorI = elevatorTypeFactory.getStrategy(elevatorType);

            Elevator elevator = elevatorI.createElevator(elevatorRequest.getMaxFloor(),
                    elevatorRequest.getCurrentFloor());

            elevatorRepo.addElevator(elevator);

            return elevator;

        } catch (ServiceDownException e) {
            throw new ServiceDownException(e.getMessage());
        }
    }
}