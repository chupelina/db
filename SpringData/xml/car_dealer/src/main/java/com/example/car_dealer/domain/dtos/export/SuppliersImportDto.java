package com.example.car_dealer.domain.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersImportDto {
    @XmlElement(name = "supplier")
    private List<SuppliersDto> suppliers;

    public SuppliersImportDto() {
    }

    public List<SuppliersDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SuppliersDto> suppliers) {
        this.suppliers = suppliers;
    }
}
