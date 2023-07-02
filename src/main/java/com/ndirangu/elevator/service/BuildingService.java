package com.ndirangu.elevator.service;

import com.ndirangu.elevator.dto.ElevatorRequestDto;
import com.ndirangu.elevator.model.Building;

public interface BuildingService {
    Building requestElevator(ElevatorRequestDto request) throws Exception;
}
