package com.ndirangu.elevator.service.impl;

import com.ndirangu.elevator.dto.ElevatorRequestDto;
import com.ndirangu.elevator.model.Building;
import com.ndirangu.elevator.service.BuildingService;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Override
    public Building requestElevator(ElevatorRequestDto elevatorRequestDto) {

        /*
        if elevator is moving towards requested floor, open at the floor
        if elevator is still, move at most two closest to requested floor
        then move to new floor
         */
        return null;
    }
}
