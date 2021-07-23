package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.export.SuppliersDto;
import com.example.car_dealer.domain.dtos.export.SuppliersImportDto;
import com.example.car_dealer.domain.dtos.imports.LocalListSuppliersDto;
import com.example.car_dealer.domain.dtos.imports.LocalSupplierDto;
import com.example.car_dealer.domain.entities.Supplier;
import com.example.car_dealer.domain.repos.SupplierRepo;
import com.example.car_dealer.services.implementation.SupplierService;
import com.example.car_dealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final static String SUPPLIER_PATH
            = "src/main/resources/xml/suppliers.xml";
    private final SupplierRepo supplierRepo;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    public SupplierServiceImpl(SupplierRepo supplierRepo,
                               ModelMapper modelMapper, XmlParser xmlParser) {
        this.supplierRepo = supplierRepo;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;

    }

    @Override
    public void seedSuppliers() throws IOException, JAXBException {
        SuppliersImportDto suppliersImportDto = this.xmlParser.parseXml(SuppliersImportDto.class, SUPPLIER_PATH);
        for (SuppliersDto supplier : suppliersImportDto.getSuppliers()) {
            Supplier map = this.modelMapper.map(supplier, Supplier.class);
            this.supplierRepo.saveAndFlush(map);
        }
    }

    @Override
    public void findAllLocal() throws JAXBException {
        String path = "src/main/resources/xml/exported/local-suppliers.xml";
        List<LocalSupplierDto> dtos = supplierRepo.findAllByImporter().stream()
                .map(s -> {
                    LocalSupplierDto map = new LocalSupplierDto();
                    map.setId(s.getId());
                    map.setName(s.getName());
                    map.setParts(s.getParts().size());
                    return map;
                })
                .collect(Collectors.toList());
        LocalListSuppliersDto current = new LocalListSuppliersDto();
        current.setSuppliers(dtos);
        xmlParser.exportXml(current, LocalListSuppliersDto.class, path);

    }
}
