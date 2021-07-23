package com.example.car_dealer.services.implementation;

import com.example.car_dealer.domain.entities.Car;

import java.io.IOException;
import java.util.Set;

public interface CarService {
    void seedCars() throws IOException;
    String findAllToyotas();
    String findAllCarsWithParts();
}
