package com.example.car_dealer.services;


import com.example.car_dealer.domain.dtos.export.CustomerExportDto;
import com.example.car_dealer.domain.dtos.export.CustomerListDto;
import com.example.car_dealer.domain.dtos.export.imports.CustomerOrderExportDto;
import com.example.car_dealer.domain.dtos.imports.CustomersOrderDto;
import com.example.car_dealer.domain.entities.Customer;
import com.example.car_dealer.domain.repos.CustomerRepo;
import com.example.car_dealer.services.implementation.CustomerService;
import com.example.car_dealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final static String CUSTOMER_PATH="src/main/resources/xml/customers.xml";
    private final static String EXPORT_CUSTOMER=
            "src/main/resources/xml/exported/ordered-customers.xml";
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, CustomerRepo customerRepo) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.customerRepo = customerRepo;
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
        CustomerListDto customerListDto = xmlParser.parseXml(CustomerListDto.class, CUSTOMER_PATH);
        for (CustomerExportDto current : customerListDto.getCustomers()) {
            Customer customer = modelMapper.map(current, Customer.class);
            customerRepo.saveAndFlush(customer);
        }
    }

    @Override
    public void exportOrder() throws JAXBException {
        List<CustomerOrderExportDto> collect = customerRepo.findAllByOrder().stream()
                .map(c -> modelMapper.map(c, CustomerOrderExportDto.class))
                .collect(Collectors.toList());
        CustomersOrderDto rootDto = new CustomersOrderDto();
        rootDto.setCustomers(collect);
        this.xmlParser.exportXml(rootDto, CustomersOrderDto.class,EXPORT_CUSTOMER );
    }


}
