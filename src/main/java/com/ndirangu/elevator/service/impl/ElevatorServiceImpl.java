package com.ndirangu.elevator.service.impl;

import com.ndirangu.elevator.model.Elevator;
import com.ndirangu.elevator.repository.ElevatorRepository;
import com.ndirangu.elevator.service.ElevatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
@AllArgsConstructor
public class ElevatorServiceImpl implements ElevatorService {
    static final String STATIONARY = "stationary";
    static final String ASC = "ascending";
    static final String DESC = "descending";

private final ElevatorRepository elevatorRepository;
    //can it handle more than 1 person requesting
    // at what point should we save to db
    // more logging?
    @Override
    public Elevator move(Elevator elevator,Long requestedFloor,  Long destination) {

        long distance = requestedFloor - elevator.getFloor();

        if (distance == 0){
         openDoors(elevator.getName());
        }
        else {
            //move to requested floor & open doors
            Long start = elevator.getFloor();
            elevator.setIsMoving(true);
            startCounting(start, distance, elevator, requestedFloor);
            openDoors(elevator.getName());
        }

        //move to destination
        Long distanceToDestination = destination - requestedFloor;
        elevator.setIsMoving(true);
        startCounting(requestedFloor, distanceToDestination, elevator, destination);
        openDoors(elevator.getName());
        elevator.setIsMoving(false);
        elevator.setState(STATIONARY);

        return elevatorRepository.save(elevator);
    }


    //todo: improve impl
    private void openDoors(String name){

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.info("Elevator {} Doors Open", name);
            }
        };
        timer.schedule(task, 0, 1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                log.info("Elevator {} Doors Closed", name);
            }
        }, 2000);
    }


    //todo: improve impl
    private void startCounting(Long start, Long distance, Elevator elevator, Long destination) {

        Timer timer = new Timer();

        if (distance > 0){
            elevator.setState(ASC);
        }else {
            elevator.setState(DESC);
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
               elevator.setFloor(start + direction(distance));

               log.info("Elevator {} is currently at floor {} moving {}", elevator.getName(), elevator.getFloor(), elevator.getState());

               if (Objects.equals(elevator.getFloor(), destination)){
                   timer.cancel();
               }
            }
        };

        timer.schedule(task, 0, 5000);
    }

    private int direction( Long distance){
        if (distance > 0){
            return 1;
        }else {
            return -1;
        }
    }
}
