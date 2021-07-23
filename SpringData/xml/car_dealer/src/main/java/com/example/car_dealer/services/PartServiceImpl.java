package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.export.PartExportDto;
import com.example.car_dealer.domain.dtos.export.PartsRootDto;
import com.example.car_dealer.domain.entities.Part;
import com.example.car_dealer.domain.entities.Supplier;
import com.example.car_dealer.domain.repos.PartRepo;
import com.example.car_dealer.domain.repos.SupplierRepo;
import com.example.car_dealer.services.implementation.PartService;
import com.example.car_dealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final static String PART_PATH = "src/main/resources/xml/parts.xml";

    private final ModelMapper modelMapper;
    private final PartRepo partRepo;
    private final SupplierRepo supplierRepo;
    private final XmlParser xmlParser;

    public PartServiceImpl(ModelMapper modelMapper, PartRepo partRepo, SupplierRepo supplierRepo, XmlParser xmlParser) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.partRepo = partRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public void seedParts() throws  JAXBException {
        PartsRootDto dto = xmlParser.parseXml(PartsRootDto.class, PART_PATH);
        for (PartExportDto current : dto.getParts()) {
            Part part = modelMapper.map(current, Part.class);
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
