package com.example.springlab.repositories;

import com.example.springlab.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {


}
