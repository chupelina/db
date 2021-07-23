package com.example.car_dealer.domain.dtos.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportDto {
    @XmlElement(name = "sale")
    private List<SaleDetailsDto> sales;

    public SaleExportDto() {
    }

    public List<SaleDetailsDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleDetailsDto> sales) {
        this.sales = sales;
    }
}
