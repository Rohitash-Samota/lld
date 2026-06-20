package com.example.lld.parking.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lld.parking.dto.Vehicle;
import com.example.lld.parking.enums.SpotType;
import com.example.lld.parking.enums.VehicleType;

@Service
public class VehicleService {

    private final List<Vehicle> vehicles = new ArrayList<>();

    public Vehicle createVehicle(
            String rcNumber,
            VehicleType vehicleType,
            SpotType spotNeeded) {

        Vehicle vehicle = new Vehicle(
                rcNumber,
                vehicleType,
                spotNeeded);

        vehicles.add(vehicle);

        return vehicle;
    }

    public Vehicle getVehicle(String rcNumber) {
        for (Vehicle vehicle : vehicles) {

            if (vehicle.getRCNumber().equals(rcNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> getVehiclesBasedOnSpotType(SpotType spotType) {

        List<Vehicle> filteredVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getSpotNeeded() == spotType) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }
}
