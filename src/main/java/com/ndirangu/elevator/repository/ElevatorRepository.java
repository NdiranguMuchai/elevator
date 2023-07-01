package com.ndirangu.elevator.repository;

import com.ndirangu.elevator.model.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
}
