package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    void findAllAuthorsWhitNameEndsWith(String text);
    List<Author> findAllWithLastNameStartsWith(String text);
    void printAllAuthorsByBookCopies();
    void getAllAuthorsWithBooks(String first, String second);
}
