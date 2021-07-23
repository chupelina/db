package com.example.car_dealer.domain.repos;

import com.example.car_dealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    @Query("select s from Supplier s where s.isImporter = false ")
    Set<Supplier> findAllByImporter();
}
