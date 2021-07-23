package com.example.springlab.repositories;

import com.example.springlab.entities.Author;
import com.example.springlab.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    @Query("select a from Author as a order by a.books.size desc")
    List<Author> findAllByCountBooks();

}
