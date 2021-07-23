package com.example.car_dealer.domain.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsListDto {
    @XmlElement(name = "car")
    private List<CarExportDto> cars;

    public CarsListDto() {
    }

    public List<CarExportDto> getCars() {
        return cars;
    }

    public void setCars(List<CarExportDto> cars) {
        this.cars = cars;
    }
}
