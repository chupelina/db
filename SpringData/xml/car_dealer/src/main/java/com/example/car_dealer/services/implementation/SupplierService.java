package com.example.car_dealer.services.implementation;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SupplierService {
    void seedSuppliers() throws IOException, JAXBException;

    void findAllLocal() throws JAXBException;
}
