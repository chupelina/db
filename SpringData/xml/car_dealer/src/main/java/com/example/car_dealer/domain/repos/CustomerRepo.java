package com.example.car_dealer.domain.repos;

import com.example.car_dealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
@Query("select c from Customer c order by c.birthDate , c.isYoungDriver desc ")
Set<Customer> findAllByOrder();
}
