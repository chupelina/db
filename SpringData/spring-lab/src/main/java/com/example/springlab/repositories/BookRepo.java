package com.example.springlab.repositories;

import com.example.springlab.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book>findAllByReleaseDateAfter(LocalDate localDate);
    List<Book>findAllByReleaseDateBefore(LocalDate localDate);
    @Query("select b from Book as b where b.author.id=4 order by b.releaseDate desc, " +
            " b.title ")
    List<Book> findByAuthor();
}
