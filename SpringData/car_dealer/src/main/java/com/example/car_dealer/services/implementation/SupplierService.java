package com.example.car_dealer.services.implementation;

import java.io.IOException;

public interface SupplierService {
    void seedSuppliers() throws IOException;

    String findAllLocal();
}
