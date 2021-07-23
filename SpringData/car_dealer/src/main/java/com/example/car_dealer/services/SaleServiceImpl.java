package com.example.car_dealer.services;

import com.example.car_dealer.domain.dtos.CarForSalesDto;
import com.example.car_dealer.domain.dtos.CustomersTotalSalesDto;
import com.example.car_dealer.domain.dtos.SaleDiscountDto;
import com.example.car_dealer.domain.entities.Car;
import com.example.car_dealer.domain.entities.Customer;
import com.example.car_dealer.domain.entities.Part;
import com.example.car_dealer.domain.entities.Sale;
import com.example.car_dealer.domain.repos.CarRepo;
import com.example.car_dealer.domain.repos.CustomerRepo;
import com.example.car_dealer.domain.repos.SaleRepo;
import com.example.car_dealer.services.implementation.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;
    private final SaleRepo saleRepo;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(CarRepo carRepo, CustomerRepo customerRepo, SaleRepo saleRepo, Gson gson, ModelMapper modelMapper) {
        this.carRepo = carRepo;
        this.customerRepo = customerRepo;
        this.saleRepo = saleRepo;
        this.gson = gson;
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
    public String findTotalSales() {
        List<CustomersTotalSalesDto> exits = new ArrayList<>();
        for (int i = 0; i < customerRepo.count(); i++) {
            List<Sale> all = saleRepo.findAllByCustomer_Id(i);

            if (all.isEmpty()) {
                continue;
            } else {
                Customer customer = customerRepo.findById((long) i).orElse(null);
                int cars = 0;

                BigDecimal total = new BigDecimal(0);
                for (Sale sale : all) {
                    cars++;
                    Set<Part> parts = sale.getCar().getParts();
                    BigDecimal money = new BigDecimal(0);
                    for (Part part : parts) {
                        money = money.add(part.getPrice());
                    }
                    total = total.add(money.multiply(BigDecimal
                            .valueOf(1 - sale.getDiscount())));
                }
                CustomersTotalSalesDto current = new CustomersTotalSalesDto();
                current.setName(customer.getName());
                current.setCars(cars);
                current.setSpentMoney(String.format("%.2f",total));
                exits.add(current);
            }
        }
        List<CustomersTotalSalesDto> last = exits.stream().sorted((l, r)->{
              int result=r.getSpentMoney().compareTo(l.getSpentMoney());
              if(result==0){
                  return r.getCars() - l.getCars();
              }
              return result;
        })
                .collect(Collectors.toList());
        return gson.toJson(last);
    }

    @Override
    public String findAllSales() {
        List<Sale> sales = saleRepo.findAll();
        List<SaleDiscountDto> output = new ArrayList<>();
        for (Sale sale : sales) {
           SaleDiscountDto current = new SaleDiscountDto();
            CarForSalesDto car = modelMapper.map(sale.getCar(),CarForSalesDto.class);
            current.setCar(car);
            current.setName(sale.getCustomer().getName());
            current.setDiscount(sale.getDiscount());
            Set<Part> parts = sale.getCar().getParts();
            BigDecimal price = new BigDecimal(0);
            for (Part part : parts) {
               price= price.add(part.getPrice());
            }
            current.setPrice(price);
            current.setPriceWithDiscount(price.multiply(
                    BigDecimal.valueOf(1-sale.getDiscount())));
            output.add(current);
        }
        return gson.toJson(output);
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
