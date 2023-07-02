package com.ndirangu.elevator.service;

import com.ndirangu.elevator.model.Elevator;

public interface ElevatorService {
    Elevator move(Elevator elevator,Long requestedFloor,  Long destination);
}
