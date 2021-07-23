package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.seeds.PartsSeedDto;
import com.example.car_dealer.domain.entities.Part;
import com.example.car_dealer.domain.entities.Supplier;
import com.example.car_dealer.domain.repos.PartRepo;
import com.example.car_dealer.domain.repos.SupplierRepo;
import com.example.car_dealer.services.implementation.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final static String PART_PATH = "src/main/resources/data/parts.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final PartRepo partRepo;
    private final SupplierRepo supplierRepo;

    public PartServiceImpl(Gson gson, ModelMapper modelMapper, PartRepo partRepo, SupplierRepo supplierRepo) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.partRepo = partRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public void seedParts() throws IOException {
     String current =String.join("",Files.readAllLines(Path.of(PART_PATH)));
        //    { "name":"Fascia", "price":100.34, "quantity":10},
        PartsSeedDto[] dtos = gson.fromJson(current, PartsSeedDto[].class);
        for (PartsSeedDto dto : dtos) {
            Part part = modelMapper.map(dto, Part.class);
            part.setSupplier(supplier());
            partRepo.saveAndFlush(part);
        }
    }

    private Supplier supplier(){
        Random random = new Random();
        int n = random.nextInt((int)supplierRepo.count())+1;
        return  supplierRepo.findById((long)n).orElse(supplierRepo.getOne((long)1));
    }
}
