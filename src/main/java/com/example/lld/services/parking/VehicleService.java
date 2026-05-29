package com.example.lld.services.parking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lld.dto.parking.Vehicle;
import com.example.lld.enums.parking.SpotType;
import com.example.lld.enums.parking.VehicleType;

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
