package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.imports.*;
import com.example.car_dealer.domain.entities.Car;
import com.example.car_dealer.domain.entities.Customer;
import com.example.car_dealer.domain.entities.Part;
import com.example.car_dealer.domain.entities.Sale;
import com.example.car_dealer.domain.repos.CarRepo;
import com.example.car_dealer.domain.repos.CustomerRepo;
import com.example.car_dealer.domain.repos.SaleRepo;
import com.example.car_dealer.services.implementation.SaleService;
import com.example.car_dealer.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    private final SaleRepo saleRepo;
    private final XmlParser parser;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(CarRepo carRepo, CustomerRepo customerRepo,
                           SaleRepo saleRepo, XmlParser parser, ModelMapper modelMapper) {
        this.carRepo = carRepo;
        this.customerRepo = customerRepo;
        this.saleRepo = saleRepo;
        this.parser = parser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSales() {
        for (int i = 0; i < 40; i++) {
            Sale sale = new Sale();
            sale.setCustomer(findCustomer());
            sale.setCar(findCar());
            sale.setDiscount(findDiscount());
            saleRepo.saveAndFlush(sale);
        }

    }

    @Override
    public void findTotalSales() throws JAXBException {
        List<Sale> customerWithCar = saleRepo.findCustomerWithCar();
        CustomerRootDto output = new CustomerRootDto();
        List<CustomerTotalSaleDto> current = new ArrayList<>();
        Customer customer = customerWithCar.get(0).getCustomer();
        BigDecimal price = BigDecimal.valueOf(0);
        BigDecimal total = BigDecimal.valueOf(0);
        int carCount = 0;
        for (int i = 0; i < customerWithCar.size(); i++) {
            Sale sale = customerWithCar.get(i);
            Customer one = sale.getCustomer();
            if (one.getId().equals(customer.getId())) {
                for (Part part : sale.getCar().getParts()) {
                    price = price.add(part.getPrice());
                }
                total = total.add(price.multiply(
                        BigDecimal.valueOf(1 - sale.getDiscount())));
                carCount++;
            } else {
                CustomerTotalSaleDto customerDto = new CustomerTotalSaleDto();
                customerDto.setName(customer.getName());
                customerDto.setCars(carCount);
                customerDto.setMoneySpent(total);
                customer = one;
                price = BigDecimal.valueOf(0);
                total = BigDecimal.valueOf(0);
                carCount = 0;
                i--;
                current.add(customerDto);
            }
        }
        current =current.stream().sorted((l,r)->r.getMoneySpent().compareTo(l.getMoneySpent()))
                .collect(Collectors.toList());
        output.setCustomer(current);
        String path = "src/main/resources/xml/exported/customers-total-sales.xml";
        parser.exportXml(output, CustomerRootDto.class,path );
    }

    @Override
    public void findAllSales() throws JAXBException {
        List<Sale> all = saleRepo.findAll();
        List<SaleDetailsDto> current = new ArrayList<>();
        for (Sale sale : all) {
            SaleDetailsDto saleDetailsDto = new SaleDetailsDto();
            saleDetailsDto.setDiscount(sale.getDiscount());
            saleDetailsDto.setCar(modelMapper.map(sale.getCar(), CarExportDto.class));
            saleDetailsDto.setName(sale.getCustomer().getName());
            BigDecimal sum = BigDecimal.valueOf(0);
            for(Part part :sale.getCar().getParts()){
                sum = sum.add(part.getPrice());
            }
            saleDetailsDto.setPrice(sum);
            saleDetailsDto.setPriceWithDiscount(sum.multiply(BigDecimal
                    .valueOf(1-sale.getDiscount())));
            current.add(saleDetailsDto);
        }
     SaleExportDto saleExportDto = new SaleExportDto();
        saleExportDto.setSales(current);
        String path= "src/main/resources/xml/exported/sales-discounts.xml";
        parser.exportXml(saleExportDto, SaleExportDto.class, path);
    }

    private double findDiscount() {
        Random random = new Random();
        List<Double> disc = discounts();
        int index = random.nextInt(disc.size());
        return disc.get(index);
    }

    private List<Double> discounts() {
        List<Double> discounts = new ArrayList<>();
        discounts.add(0.0);
        discounts.add(0.05);
        discounts.add(0.1);
        discounts.add(0.15);
        discounts.add(0.20);
        discounts.add(0.30);
        discounts.add(0.40);
        discounts.add(0.50);
        return discounts;
    }

    private Car findCar() {
        Random random = new Random();
        int index = random.nextInt((int) carRepo.count()) + 1;
        return carRepo.findById((long) index).get();
    }

    private Customer findCustomer() {
        Random random = new Random();
        int index = random.nextInt((int) customerRepo.count()) + 1;
        return customerRepo.findById((long) index).get();
    }
}
