package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.CarExportDto;
import com.example.car_dealer.domain.dtos.CarsDto;
import com.example.car_dealer.domain.dtos.PartsForCarsDto;
import com.example.car_dealer.domain.dtos.seeds.CarSeedDto;
import com.example.car_dealer.domain.entities.Car;
import com.example.car_dealer.domain.entities.Part;
import com.example.car_dealer.domain.repos.CarRepo;
import com.example.car_dealer.domain.repos.PartRepo;
import com.example.car_dealer.services.implementation.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {
    private final static String CAR_PATH= "src/main/resources/data/cars.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final CarRepo carRepo;
    private final PartRepo partRepo;

    public CarServiceImpl(Gson gson, ModelMapper modelMapper, CarRepo carRepo, PartRepo partRepo) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.carRepo = carRepo;
        this.partRepo = partRepo;
    }

    @Override
    public void seedCars() throws IOException {
      String current =String.join("", Files.readAllLines(Path.of(CAR_PATH)));
        CarSeedDto[] carSeedDto= gson.fromJson(current, CarSeedDto[].class);

        for (CarSeedDto seedDto : carSeedDto) {
            Car car = modelMapper.map(seedDto, Car.class);
            car.setParts(parts());
            Set<Part> parts = car.getParts();
            carRepo.saveAndFlush(car);
            for (Part part : parts) {
                part.getCars().add(car);
                partRepo.save(part);
            }

        }
    }

    @Override
    public String findAllToyotas() {
        Set<Car> cars = carRepo
                .findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarExportDto> toExport = new ArrayList<>();
        for (Car car : cars) {
            CarExportDto carExportDto = modelMapper.map(car, CarExportDto.class);
            toExport.add(carExportDto);
        }
        return gson.toJson(toExport);
    }

    @Override
    public String findAllCarsWithParts() {
        List<Car> all = carRepo.findAll();
        List<CarsDto> carsDtos = new ArrayList<>();
        for (Car car : all) {
            CarsDto carsDto = modelMapper.map(car, CarsDto.class);
            carsDto.getParts().forEach(part->
                    modelMapper.map(part, PartsForCarsDto.class));
            carsDtos.add(carsDto);
        }
        return gson.toJson(carsDtos);
    }

    private Set<Part> parts(){
        Set<Part> parts = new HashSet<>();
        Random random = new Random();
        int n = random.nextInt(1)+1;
        for (int i = 0; i < n; i++) {
           int m = random.nextInt((int)partRepo.count())+1;
           Part part = partRepo.findById((long)m).orElse(null) ;
            parts.add(part);
        }
        return parts;
    }
}
