package com.ndirangu.elevator.repository;

import com.ndirangu.elevator.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
