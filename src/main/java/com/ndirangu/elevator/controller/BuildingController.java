package com.ndirangu.elevator.controller;

import com.ndirangu.elevator.dto.ElevatorRequestDto;
import com.ndirangu.elevator.model.Building;
import com.ndirangu.elevator.service.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/building")
@AllArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping("/request-elevator")
    public Building requestElevator(@RequestBody ElevatorRequestDto request) throws Exception{
        return buildingService.requestElevator(request);
    }

    @GetMapping("/{id}")
    public Building findById(@PathVariable Long id) throws Exception{
        return buildingService.findById(id);
    }

    @PostMapping("/create")
    public  Building create(@RequestBody Building building){
        return buildingService.create(building);
    }

    @GetMapping({"", "/"})
    public Page<Building> findAll(Pageable pageable){
        return buildingService.findAll(pageable);
    }

    @PutMapping("/update")
    public Building update(@RequestBody Building building){
        return buildingService.update(building);
    }
}
