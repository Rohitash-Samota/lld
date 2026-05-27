package com.example.lld.dto.parking;

import com.example.lld.enums.parking.LevelType;

public class Level {

    private String levelId;
    private LevelType level;
    private ParkingSpot[] spots;
    private int totalSpots;
    private int availableSpots;

    public Level(String levelId, int numberRow, int spotPerRow) {
        this.levelId = levelId;
        this.totalSpots = totalSpots(numberRow, spotPerRow);
        this.availableSpots = this.totalSpots;
        InitializeLevel();
    }

    public ParkingSpot[] getAvailableSpots() {
        return this.spots;
    }

    private int totalSpots(int numberRow, int spotPerRow) {
        return numberRow * spotPerRow;
    }

    private void InitializeLevel() {
    }
}
