package com.example.car_dealer.domain.dtos.imports;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsListDto {
    @XmlElement(name = "part")
    private List<PartForCarDto> parts;

    public PartsListDto() {
    }

    public List<PartForCarDto> getParts() {
        return parts;
    }

    public void setParts(List<PartForCarDto> parts) {
        this.parts = parts;
    }
}
