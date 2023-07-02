package com.ndirangu.elevator.controller;

import com.ndirangu.elevator.dto.MoveElevatorDto;
import com.ndirangu.elevator.model.Elevator;
import com.ndirangu.elevator.service.ElevatorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elevator")
@AllArgsConstructor
public class ElevatorController {
    private final ElevatorService elevatorService;
    @PostMapping("/move")
    public Elevator move (@RequestBody MoveElevatorDto request){
        return elevatorService.move(request.getElevator(), request.getRequestedFloor(), request.getDestination());
    }

    @GetMapping({"", "/"})
    public Page<Elevator> findAll(Pageable pageable){
        return elevatorService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public  Elevator findOne(@PathVariable Long id)throws Exception{
        return elevatorService.findOne(id);
    }

    @PostMapping("/save")
    public Elevator createOrUpdate(@RequestBody Elevator elevator){
        return elevatorService.createOrUpdate(elevator);
    }
}
