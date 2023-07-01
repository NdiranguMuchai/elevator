package com.ndirangu.elevator.dto;

import lombok.Data;

@Data
public class ElevatorRequestDto {
    private Long currentFloor;
    private Long destinationFloor;
}
