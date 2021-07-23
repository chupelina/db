package com.example.car_dealer.domain.repos;

import com.example.car_dealer.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepo extends JpaRepository<Part, Long> {
}
