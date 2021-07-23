package com.example.springlab.servises;

import com.example.springlab.entities.Author;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById( long id);
    List<Author> findAllByCountBooks();

    List<Author> findAll();
}
