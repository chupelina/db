package com.example.car_dealer.domain.repos;

import com.example.car_dealer.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Set<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
