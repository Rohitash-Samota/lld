package com.example.lld.parking.dto;

import com.example.lld.parking.enums.SpotType;
import com.example.lld.parking.enums.VehicleType;

public class Vehicle {

    private String RCNumber;
    private VehicleType vehicleType;
    private SpotType spotNeeded;

    public Vehicle(String RCNumber, VehicleType vehicleType, SpotType spotNeeded) {
        this.RCNumber = RCNumber;
        this.spotNeeded = spotNeeded;
        this.vehicleType = vehicleType;
    }

    public String getRCNumber() {
        return RCNumber;
    }

    public void setRCNumber(String RCNumber) {
        this.RCNumber = RCNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public SpotType getSpotNeeded() {
        return spotNeeded;
    }

    public void setSpotNeeded(SpotType spotNeeded) {
        this.spotNeeded = spotNeeded;
    }
}
