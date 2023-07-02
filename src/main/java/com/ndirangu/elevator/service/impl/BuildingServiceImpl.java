package com.ndirangu.elevator.service.impl;

import com.ndirangu.elevator.dto.ElevatorRequestDto;
import com.ndirangu.elevator.model.Building;
import com.ndirangu.elevator.model.Elevator;
import com.ndirangu.elevator.repository.BuildingRepository;
import com.ndirangu.elevator.service.BuildingService;
import com.ndirangu.elevator.service.ElevatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class BuildingServiceImpl implements BuildingService {
private final BuildingRepository buildingRepository;
private final ElevatorService elevatorService;
    static final String STATIONARY = "stationary";
    static final String ASC = "ascending";
    static final String DESC = "descending";

    @Override
    @Transactional
    public Building requestElevator(ElevatorRequestDto request) throws Exception {

        Building building = buildingRepository.findById(request.getBuildingId())
                .orElseThrow(() -> new Exception("We do not recognise the building"));


        if (Objects.equals(request.getCurrentFloor(), building.getLowestFloor())
                && request.getDirection().equalsIgnoreCase("down")){
            throw new RuntimeException("You are at lowest floor");
        }

        if (Objects.equals(request.getCurrentFloor(), building.getHighestFloor())
                && request.getDirection().equalsIgnoreCase("up")){
            throw new RuntimeException("You are at highest floor");
        }

        /*
        find available elevators
       ideal conditions:
         - moving down and current floor > requested floor
         - moving up and current floor < requested floor
         - TODO: for first two confirm first whether person is going up or down
         - stationary && abs(current floor - requested floor) < 2

         if none found call all
         */

        for (Elevator elevator: building.getElevators()){
            log.info("Elevator {} is currently at floor {} is {}", elevator.getName(), elevator.getFloor(), elevator.getState());
        }
        List<Elevator> idealElevators = building.getElevators()
                .stream()
                .filter(elevator ->
                        (elevator.getState().equalsIgnoreCase(DESC) && elevator.getFloor() > request.getCurrentFloor())
                                || (elevator.getState().equalsIgnoreCase(ASC) && elevator.getFloor() < request.getCurrentFloor())
                                || (elevator.getState().equalsIgnoreCase(STATIONARY)) && Math.abs(elevator.getFloor() - request.getCurrentFloor()) < 2)
                .toList();

        if (!idealElevators.isEmpty()){
            //change it to choose elevator at random
            elevatorService.move(idealElevators.get(0), request.getCurrentFloor(), request.getDestinationFloor());
        }else {
            //same here
            elevatorService.move(building.getElevators().get(0), request.getCurrentFloor(), request.getDestinationFloor());
        }

        return buildingRepository.save(building);
    }


    @Override
    public Building findById(Long id) throws Exception {
       return buildingRepository.findById(id).orElseThrow(() -> new Exception("We do not recognise the building"));
    }

    @Override
    public Building create(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public Page<Building> findAll(Pageable pageable) {
        return buildingRepository.findAll(pageable);
    }

    @Override
    public Building update(Building building) {
        return buildingRepository.save(building);
    }
}
