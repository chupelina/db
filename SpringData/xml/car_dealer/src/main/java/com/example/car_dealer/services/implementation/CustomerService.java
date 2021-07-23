package com.example.car_dealer.services.implementation;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CustomerService {
    void seedCustomers() throws IOException, JAXBException;

    void exportOrder() throws JAXBException;
}
