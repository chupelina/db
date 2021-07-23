package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.repozitory.AuthorRepo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements AuthorService {
    private final FileUtil fileUtil;
    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepo authorRepo) {
        this.fileUtil = fileUtil;
        this.authorRepo = authorRepo;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepo.count() == 0) {
            String[] lines = fileUtil.readFileContent
                    (GlobalConstants.AUTHORS_FILE_PATH);
            for (String line : lines) {
                String[] tokens = line.split("\\s+");
                Author author = new Author(tokens[0], tokens[1]);
                authorRepo.saveAndFlush(author);
            }
        }
    }

    @Override
    public void findAllAuthorsWhitNameEndsWith(String text) {
        List<Author> authors = new ArrayList<>(authorRepo
                .findAllByFirstNameEndingWith(text));
        if (authors.size() == 0) {
            System.out.println("There is no such authors");
        } else {
            authors.forEach(
                    author -> System.out.println(author.getFirstName() +
                            " " + author.getLastName())
            );
        }
    }

    @Override
    public List<Author> findAllWithLastNameStartsWith(String text) {
        return new ArrayList<>(authorRepo.findAllByLastNameStartingWith(text));
    }

    @Override
    public void printAllAuthorsByBookCopies() {
        List<Author> authors = authorRepo.findAll();
        Map<Integer, String> authorCopies = new TreeMap<>(Comparator.reverseOrder());

        authors.forEach(a -> {
            int copies = a.getBooks().stream().mapToInt(Book::getCopies).sum();
            authorCopies.put(copies, a.getFirstName() + " " + a.getLastName());
        });
        for (Map.Entry<Integer, String> entry : authorCopies.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }

    @Override
    public void getAllAuthorsWithBooks(String first, String second) {
        Author author = authorRepo.findFirstByFirstNameAndLastName(first, second);
        if (author == null) {
            System.out.println("Sorry I can not find that author.");
        } else {
            int n = author.getBooks().size();
            if (n > 1) {
                System.out.printf("%s %s has written %d books%n", first, second, n);
            } else if (n == 1) {
                System.out.printf("%s %s has written %d book%n", first, second, n);
            } else {
                System.out.printf("%s %s has not written a single book.%n", first, second);
            }
        }
    }
}
