package com.example.car_dealer.domain.repos;

import com.example.car_dealer.domain.entities.Customer;
import com.example.car_dealer.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {


    List<Sale> findAllByCustomer_Id(long id);
}
