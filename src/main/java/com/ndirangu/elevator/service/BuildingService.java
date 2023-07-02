package com.ndirangu.elevator.service;

import com.ndirangu.elevator.dto.ElevatorRequestDto;
import com.ndirangu.elevator.model.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuildingService {
    Building requestElevator(ElevatorRequestDto request) throws Exception;
    Building findById(Long id)throws Exception;

    Building create(Building building);

    Page<Building> findAll(Pageable pageable);

    Building update(Building building);
}
