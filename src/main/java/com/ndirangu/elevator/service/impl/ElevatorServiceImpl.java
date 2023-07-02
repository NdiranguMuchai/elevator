package com.ndirangu.elevator.service.impl;

import com.ndirangu.elevator.model.Elevator;
import com.ndirangu.elevator.repository.ElevatorRepository;
import com.ndirangu.elevator.service.ElevatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Elevator move(Elevator elevator, Long requestedFloor, Long destination) {

        long distance = requestedFloor - elevator.getFloor();

        if (distance == 0) {
            openDoors(elevator.getName());
        } else {
            // move to requested floor & open doors
            Long start = elevator.getFloor();
            elevator.setIsMoving(true);
            startCounting(start, distance, elevator, requestedFloor);
            openDoors(elevator.getName());

            // Delay before moving to the destination
            try {
                Thread.sleep(5000); // Adjust the delay as needed
            } catch (InterruptedException e) {
                log.error("Thread interrupted while waiting for the first move to complete.", e);
            }
        }

        // move to destination
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
        String state;

        if (distance > 0) {
            state = ASC;
            elevator.setState(state);
        } else {
            state = DESC;
            elevator.setState(state);
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Long temp = start;

                if (isPositive(distance)) {
                    temp++;
                } else {
                    temp--;
                }

                elevator.setFloor(temp);

                log.info("Elevator {} is currently at floor {} is {}", elevator.getName(), temp, state);

                if (Objects.equals(elevator.getFloor(), destination)) {
                    timer.cancel();
                    openDoors(elevator.getName());
                }
            }
        };

        timer.schedule(task, 0, 5000);
    }


    private boolean isPositive( Long distance){
        return distance >0;
    }

    @Override
    public Page<Elevator> findAll(Pageable pageable) {
        return elevatorRepository.findAll(pageable);
    }

    @Override
    public Elevator findOne(Long id)  throws Exception{
        return elevatorRepository.findById(id).orElseThrow(() -> new Exception("Elevator not found"));
    }

    @Override
    public Elevator createOrUpdate(Elevator elevator) {
        return elevatorRepository.save(elevator);
    }

}
