package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.export.CarExportDto;
import com.example.car_dealer.domain.dtos.export.CarsListDto;
import com.example.car_dealer.domain.dtos.imports.*;
import com.example.car_dealer.domain.entities.Car;
import com.example.car_dealer.domain.entities.Part;
import com.example.car_dealer.domain.repos.CarRepo;
import com.example.car_dealer.domain.repos.PartRepo;
import com.example.car_dealer.services.implementation.CarService;
import com.example.car_dealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final static String CAR_PATH= "src/main/resources/xml/cars.xml";
    private final static String TOYOTA_PATH= "src/main/resources/xml/exported/all-toyotas.xml";
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final CarRepo carRepo;
    private final PartRepo partRepo;

    public CarServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, CarRepo carRepo, PartRepo partRepo) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.carRepo = carRepo;
        this.partRepo = partRepo;
    }

    @Override
    public void seedCars() throws IOException, JAXBException {
        CarsListDto carsListDto = xmlParser.parseXml(CarsListDto.class, CAR_PATH);
        for (CarExportDto current : carsListDto.getCars()) {
            Car car = modelMapper.map(current, Car.class);
            car.setParts(parts());
            carRepo.saveAndFlush(car);
        }
    }

    @Override
    public void findAllToyotas() throws JAXBException {
        List<CarToyotaDto> toyota = carRepo.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota").stream()
                .map(c -> modelMapper.map(c, CarToyotaDto.class)).collect(Collectors.toList());
        CarListToyotaDto cars = new CarListToyotaDto();
        cars.setCars(toyota);
        xmlParser.exportXml(cars, CarListToyotaDto.class, TOYOTA_PATH);

    }

    @Override
    public void findAllCarsWithParts() throws JAXBException {
        String path = "src/main/resources/xml/exported/cars-and-parts.xml";
        List<CarsForPartsDto> collect = carRepo.findAll().stream().map(car -> {
            CarsForPartsDto current = modelMapper.map(car, CarsForPartsDto.class);
            PartForCarDto[] partForCarDto = modelMapper.map(car.getParts(), PartForCarDto[].class);
            PartsListDto partsListDto = new PartsListDto();
            partsListDto.setParts(Arrays.asList(partForCarDto));
            current.setPartsListDto(partsListDto);
            return current;
        }).collect(Collectors.toList());
        CarsToExportDto output = new CarsToExportDto();
        output.setCars(collect);

        xmlParser.exportXml(output, CarsToExportDto.class, path);
    }

    private Set<Part> parts(){
        Set<Part> parts = new HashSet<>();
        Random random = new Random();
        int n = random.nextInt(2)+1;
        for (int i = 0; i < n; i++) {
           int m = random.nextInt((int)partRepo.count())+1;
           Part part = partRepo.findById((long)m).orElse(null) ;
            parts.add(part);
        }
        return parts;
    }
}
