package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.imports.CarSeedDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidatorUtilImpl;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtilImpl validatorUtilImpl;

    @Autowired
    public CarServiceImpl(CarRepository carRepo, ModelMapper modelMapper, Gson gson, ValidatorUtilImpl validatorUtilImpl) {
        this.carRepo = carRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;

        this.validatorUtilImpl = validatorUtilImpl;
    }

    @Override
    public boolean areImported() {
        return carRepo.count()>0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return null;
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder sb = new StringBuilder();
        String path = "src/main/resources/files/json/cars.json";
        String current= String.join("", Files.readAllLines(Path.of(path)));
        CarSeedDto[] cars = gson.fromJson(current, CarSeedDto[].class);
        for (CarSeedDto car : cars) {
            Car one = modelMapper.map(car, Car.class);
            if(validatorUtilImpl.isValid(one)){
                sb.append(String.format("Successfully imported car - %s - %s%n",
                        one.getMake(), one.getModel()));
                carRepo.saveAndFlush(one);
            }else{
                sb.append("Invalid car").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();
        Set<Car> cars = carRepo.exportCars();
        for (Car car : cars) {
            sb.append(String.format("Car make - %s, model - %s", car.getMake(), car.getModel())).append(System.lineSeparator());
            sb.append(String.format("\tKilometers - %d", car.getKilometers())).append(System.lineSeparator());
            sb.append(String.format("\tRegistered on - %s", car.getRegisteredOn())).append(System.lineSeparator());
            sb.append(String.format("\tNumber of pictures - %s", car.getPictures().size())).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
