package com.example.car_dealer.services.implementation;

import javax.xml.bind.JAXBException;

public interface SaleService {
    void seedSales();

    void findTotalSales() throws JAXBException;
    void findAllSales() throws JAXBException;
}
