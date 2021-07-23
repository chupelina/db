package com.example.car_dealer.domain.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerListDto {
    @XmlElement(name = "customer")
    private List<CustomerExportDto> customers;

    public CustomerListDto() {
    }

    public List<CustomerExportDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerExportDto> customers) {
        this.customers = customers;
    }
}
