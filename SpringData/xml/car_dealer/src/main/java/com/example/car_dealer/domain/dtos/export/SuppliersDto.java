package com.example.car_dealer.domain.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersDto {

    @XmlAttribute
    private String name;
    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SuppliersDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setIsImporter(boolean imported) {
        isImporter = imported;
    }
}
