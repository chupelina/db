package com.example.car_dealer.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private long travelledDistance;
    @ManyToMany(fetch =  FetchType.EAGER)
//    @JoinTable(name = "cars_parts",
//    joinColumns = @JoinColumn(name = "car_id"),
//    inverseJoinColumns = @JoinColumn(name = "part_id"))
    private Set<Part> parts;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
