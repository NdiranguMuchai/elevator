package com.ndirangu.elevator.dataLoader;

import com.ndirangu.elevator.model.Building;
import com.ndirangu.elevator.model.Elevator;
import com.ndirangu.elevator.repository.BuildingRepository;
import com.ndirangu.elevator.repository.ElevatorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInit implements CommandLineRunner {
    private final BuildingRepository buildingRepository;
    private final ElevatorRepository elevatorRepository;

    @Override
    public void run(String... args) throws Exception {

        //Elevators

        //A
        Elevator one = new Elevator();
        one.setName("A");
        one.setFloor(2L);

        elevatorRepository.save(one);

        //B
        Elevator two = new Elevator();
        two.setName("B");
        two.setFloor(7L);
        two.setIsMoving(true);
        two.setState("descending");

        elevatorRepository.save(two);

        //C
        Elevator three = new Elevator();
        three.setName("C");
        three.setFloor(0L);

        elevatorRepository.save(three);

        //D
        Elevator four = new Elevator();
        four.setName("D");
        four.setFloor(2L);
        four.setIsMoving(true);
        four.setState("ascending");

        elevatorRepository.save(four);

        //E
        Elevator five = new Elevator();
        five.setName("E");
        five.setFloor(1L);
        five.setIsMoving(true);
        five.setState("descending");

        elevatorRepository.save(five);


        //Building
        Building building = new Building();

        List<Elevator> elevators = List.of(one, two, three, four, five);
        building.setElevators(elevators);
        building.setHighestFloor(10L);
        building.setLowestFloor(-2L);

        buildingRepository.save(building);
    }
}
