package com.example.car_dealer;

import com.example.car_dealer.services.implementation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public Runner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("If you want to seed the data press 0, if you do not press a number");
        int input = Integer.parseInt(scan.nextLine().trim());
        if (input == 0) {
            supplierService.seedSuppliers();
            partService.seedParts();
            carService.seedCars();
            customerService.seedCustomers();
            saleService.seedSales();
        }
        customerService.exportOrder();
        carService.findAllToyotas();
        supplierService.findAllLocal();
        carService.findAllCarsWithParts();
        saleService.findTotalSales();
        saleService.findAllSales();
        System.out.println("Everything will work automatically check the result in");
        System.out.println("src/main/resources/xml/exported");
        System.out.println("Thanks for your time :)");
    }
}
