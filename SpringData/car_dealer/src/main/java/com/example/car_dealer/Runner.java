package com.example.car_dealer;

import com.example.car_dealer.services.implementation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
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
        System.out.println("If you want to seed the data press 0");
        int input = Integer.parseInt(scan.nextLine());
        if (input == 0) {
            supplierService.seedSuppliers();
            partService.seedParts();
            carService.seedCars();
            customerService.seedCustomers();
            saleService.seedSales();
        }
        System.out.println("Here is my work, sorry but i can not figure out " +
                "how to solve ex 5.");
        System.out.println(" Everyone is working. Type number from 1 to 6 to " +
                "chek them and 0 to exit.");
        input = Integer.parseInt(scan.nextLine());
        while (input != 0) {
            switch (input) {
                case 1:
                    System.out.println(customerService.findAllCustomers());
                    break;
                case 2:
                    System.out.println(carService.findAllToyotas());
                    break;
                case 3:
                    System.out.println(supplierService.findAllLocal());
                    break;
                case 4:
                    System.out.println(carService.findAllCarsWithParts());
                    break;
                case 5:
                    System.out.println(saleService.findTotalSales());
                    break;
                case 6:
                    System.out.println(saleService.findAllSales());
                    break;
            }
            System.out.println("+-".repeat(100));
            System.out.println("Type the next target and 0 to exit");
            input = Integer.parseInt(scan.nextLine());
        }
        System.out.println("Thanks for your time :)");
    }
}
