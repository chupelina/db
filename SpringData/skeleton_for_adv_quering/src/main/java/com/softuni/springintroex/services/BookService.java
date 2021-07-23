package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.EditionType;

import java.io.IOException;
import java.math.BigDecimal;

public interface BookService {
    void seedBooks() throws IOException;
    void printAllBooksByAgeRestriction(String restriction);
    void findAllByEditionTypeAndCopies(String editionType,
                                       int copies);
    void findAllWhitPriceBetween(int min, int max);
    void findAllWhitReleasedYearNotIn(String year);
    void findAllWhitReleasedDateBefore(String date);
    void findAllBookTitlesContaining(String text);
    void findAllBooksWithAuthor(String text);
    void findAllBooksWithTitleMoreThan(int symbols);
    void findAllBookCopiesWithAuthor();
    void findTheRightBook(String title);
    void increaseCopies(String count, String date);
    void deleteAllBooksWithCopiesLessThen(int count);
}
