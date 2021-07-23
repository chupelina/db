package com.example.springlab.servises.impl;

import com.example.springlab.constants.GlobalConstants;
import com.example.springlab.entities.Author;
import com.example.springlab.entities.Book;
import com.example.springlab.repositories.AuthorRepo;
import com.example.springlab.repositories.BookRepo;
import com.example.springlab.servises.AuthorService;
import com.example.springlab.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(BookRepo bookRepo, AuthorRepo authorRepo, FileUtil fileUtil) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepo.count() != 0) {
            return;
        }
        String[] authors = this.fileUtil.readFileContent
                (GlobalConstants.AUTHORS_FILE_PATH);
        Arrays.stream(authors).forEach(a -> {
            String[] current = a.split("\\s+");
            Author author = new Author(current[0], current[1]);
            this.authorRepo.saveAndFlush(author);
        });
    }

    @Override
    public int getAllAuthorsCount() {

        return (int) this.authorRepo.count();
    }

    @Override
    public Author findAuthorById(long id) {

        return this.authorRepo.getOne(id);
    }

    @Override
    public List<Author> findAllByCountBooks(){
        return authorRepo.findAllByCountBooks();
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("01/01/1990", formatter);
        List<Book> books =bookRepo.findAllByReleaseDateBefore(releaseDate);
        for (Book book : books) {
            if(!authors.contains(book.getAuthor())){
                authors.add(book.getAuthor());
            }
        }
        return authors;
    }
}
