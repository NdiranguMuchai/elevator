package com.ndirangu.elevator.service.impl;

import com.ndirangu.elevator.model.Elevator;
import com.ndirangu.elevator.service.ElevatorService;
import org.springframework.stereotype.Service;

@Service
public class ElevatorServiceImpl implements ElevatorService {

    @Override
    public Elevator move() {

        /*
        if at ground floor/lowest floor cant move down
        if at highest floor cant move up

        add elevator object to method signature
        change isMoving to true
        increase or decrease value of floor every 5 secs
        add logging here?

         */
        return null;
    }
}
