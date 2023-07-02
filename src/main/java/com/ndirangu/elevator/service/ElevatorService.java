package com.ndirangu.elevator.service;

import com.ndirangu.elevator.model.Elevator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ElevatorService {
    Elevator move(Elevator elevator,Long requestedFloor,  Long destination);

    Page<Elevator> findAll(Pageable pageable);
    Elevator findOne(Long id)throws Exception;

    Elevator createOrUpdate(Elevator elevator);

}
