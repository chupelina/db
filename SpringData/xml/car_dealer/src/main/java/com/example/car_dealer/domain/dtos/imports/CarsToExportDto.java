package com.example.car_dealer.domain.dtos.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsToExportDto {
    @XmlElement(name = "car")
    private List<CarsForPartsDto> cars;

    public CarsToExportDto() {
    }

    public List<CarsForPartsDto> getCars() {
        return cars;
    }

    public void setCars(List<CarsForPartsDto> cars) {
        this.cars = cars;
    }
}
