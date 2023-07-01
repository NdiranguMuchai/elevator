package com.ndirangu.elevator.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "elevator")
public class Elevator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long floor;
    private Boolean isMoving = false;

}
