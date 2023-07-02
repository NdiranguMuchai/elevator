package com.ndirangu.elevator.dto;

import com.ndirangu.elevator.model.Building;
import lombok.Data;

@Data
public class ElevatorRequestDto {
    private Long buildingId;
    private Long currentFloor;
    private Long destinationFloor;
    private String direction;
}
