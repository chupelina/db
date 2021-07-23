package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.CustomerDto;
import com.example.car_dealer.domain.dtos.seeds.CustomerSeedDto;
import com.example.car_dealer.domain.entities.Customer;
import com.example.car_dealer.domain.repos.CustomerRepo;
import com.example.car_dealer.services.implementation.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final static String CUSTOMER_PATH="src/main/resources/data/customers.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(ModelMapper modelMapper, Gson gson, CustomerRepo customerRepo) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.customerRepo = customerRepo;
    }

    @Override
    public void seedCustomers() throws IOException {
        String current = String.join("", Files.readAllLines(Path.of(CUSTOMER_PATH)));

        CustomerSeedDto[] customerSeedDto= gson.fromJson(current, CustomerSeedDto[].class);
        for (CustomerSeedDto seedDto : customerSeedDto) {
            Customer customer = new Customer();
            customer.setName(seedDto.getName());
            customer.setYoungDriver(seedDto.isYoungDriver());
            customer.setBirthDay(seedDto.getBirthDate());
            customerRepo.saveAndFlush(customer);

        }
    }

    @Override
    public String findAllCustomers() {
        List<Customer> customers =
                customerRepo.findAllByOrder();
List<CustomerDto> toExport = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto map = this.modelMapper.map(customer, CustomerDto.class);
            map.setBirthDate(customer.getBirthDay().toString());
        toExport.add(map);
        }

        return this.gson.toJson(toExport);
    }
}
