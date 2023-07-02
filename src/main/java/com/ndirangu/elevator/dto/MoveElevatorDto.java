package com.ndirangu.elevator.dto;

import com.ndirangu.elevator.model.Elevator;
import lombok.Data;

@Data
public class MoveElevatorDto {
    private Elevator elevator;
    private Long requestedFloor;
    private Long destination;
}
