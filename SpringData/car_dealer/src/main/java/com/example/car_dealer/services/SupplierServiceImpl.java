package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.LocalSupplierDto;
import com.example.car_dealer.domain.dtos.seeds.SupplierSeedDto;
import com.example.car_dealer.domain.entities.Supplier;
import com.example.car_dealer.domain.repos.SupplierRepo;
import com.example.car_dealer.services.implementation.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final static String SUPPLIER_PATH
            = "src/main/resources/data/suppliers.json";
    private final SupplierRepo supplierRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SupplierServiceImpl(SupplierRepo supplierRepo, ModelMapper modelMapper, Gson gson) {
        this.supplierRepo = supplierRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedSuppliers() throws IOException {
        String current = String.join("",
                Files.readAllLines(Path.of(SUPPLIER_PATH)));
        SupplierSeedDto[] supplierSeedDtos =
                this.gson.fromJson(current, SupplierSeedDto[].class);

        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
            supplierRepo.saveAndFlush(
                    this.modelMapper.map(supplierSeedDto, Supplier.class));
        }

    }

    @Override
    public String findAllLocal() {
        Set<Supplier> localSuppliers = supplierRepo
                .findAllByImporter();
        List<LocalSupplierDto> locals = new ArrayList<>();
        for (Supplier localSupplier : localSuppliers) {
            LocalSupplierDto map = new LocalSupplierDto();
            map.setId(localSupplier.getId());
            map.setName(localSupplier.getName());
            map.setParts(localSupplier.getParts().size());
            locals.add(map);
        }
        return gson.toJson(locals);
    }
}
