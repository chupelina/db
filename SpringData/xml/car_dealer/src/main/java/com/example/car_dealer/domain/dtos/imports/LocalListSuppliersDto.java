package com.example.car_dealer.domain.dtos.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalListSuppliersDto {
    @XmlElement(name = "supplier")
    private List<LocalSupplierDto> suppliers;

    public LocalListSuppliersDto() {
    }

    public List<LocalSupplierDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<LocalSupplierDto> suppliers) {
        this.suppliers = suppliers;
    }
}
