package com.example.car_dealer.domain.dtos.imports;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsForPartsDto {
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;
    @XmlElement(name = "parts")
    private PartsListDto partsListDto;

    public CarsForPartsDto() {
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

    public PartsListDto getPartsListDto() {
        return partsListDto;
    }

    public void setPartsListDto(PartsListDto partsListDto) {
        this.partsListDto = partsListDto;
    }
}
