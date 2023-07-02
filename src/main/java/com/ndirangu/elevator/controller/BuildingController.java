package com.ndirangu.elevator.controller;

import com.ndirangu.elevator.dto.ElevatorRequestDto;
import com.ndirangu.elevator.model.Building;
import com.ndirangu.elevator.service.BuildingService;
import lombok.AllArgsConstructor;
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
}
