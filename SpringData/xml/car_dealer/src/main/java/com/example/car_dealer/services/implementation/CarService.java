package com.example.car_dealer.services.implementation;

import com.example.car_dealer.domain.entities.Car;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Set;

public interface CarService {
    void seedCars() throws IOException, JAXBException;
    void findAllToyotas() throws JAXBException;
    void findAllCarsWithParts() throws JAXBException;
}
