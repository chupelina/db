package com.example.springlab.servises;

import com.example.springlab.entities.Author;
import com.example.springlab.entities.Book;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks()throws IOException;
   List<Book> getAllBooksAfter2000();
    List<Book> findByAuthor();
}
