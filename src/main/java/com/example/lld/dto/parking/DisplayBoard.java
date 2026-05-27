package com.example.lld.dto.parking;

import java.util.Map;

import com.example.lld.enums.parking.SpotType;

public class DisplayBoard {

    private String boardId;
    private Map<SpotType, Integer> availableSpotsCount;
}
