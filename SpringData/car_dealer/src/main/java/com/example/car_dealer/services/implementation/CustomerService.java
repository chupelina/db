package com.example.car_dealer.services.implementation;

import java.io.IOException;

public interface CustomerService {
    void seedCustomers() throws IOException;

    String findAllCustomers();
}
