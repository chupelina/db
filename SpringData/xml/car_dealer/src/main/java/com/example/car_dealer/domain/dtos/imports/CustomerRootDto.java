package com.example.car_dealer.domain.dtos.imports;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootDto {
    @XmlElement
    private List<CustomerTotalSaleDto> customer;

    public CustomerRootDto() {
    }

    public List<CustomerTotalSaleDto> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomerTotalSaleDto> customer) {
        this.customer = customer;
    }
}
