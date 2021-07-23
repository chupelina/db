package com.example.springlab.servises.impl;

import com.example.springlab.constants.GlobalConstants;
import com.example.springlab.entities.*;
import com.example.springlab.repositories.BookRepo;
import com.example.springlab.servises.AuthorService;
import com.example.springlab.servises.BookService;
import com.example.springlab.servises.CategoryService;
import com.example.springlab.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final FileUtil fileUtil;
    private final BookRepo bookRepo;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepo bookRepo, AuthorService authorService, CategoryService categoryService) {
        this.fileUtil = fileUtil;
        this.bookRepo = bookRepo;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        //2 29/05/2002 15170 37.47 1 Cabbages and Kings
        if (this.bookRepo.count() != 0) {
            return;
        }

        String[] books = fileUtil.readFileContent(GlobalConstants.BOOKS_PATH_FILE);
        Arrays.stream(books).forEach(b -> {
            String[] current = b.split("\\s+", 6);
            Author author = this.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(current[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate releaseDate = LocalDate.parse(current[1], formatter);
            int copies = Integer.parseInt(current[2]);
            BigDecimal price = new BigDecimal(current[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(current[4])];
            String title = current[5];
            Set<Category> categories = this.getRandomCategories();
            Book book = new Book(title, editionType, price, copies,
                    releaseDate, ageRestriction, author, categories);
            this.bookRepo.saveAndFlush(book);

        });
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;
        for (int i = 1; i <= bound; i++) {
            int bound1 = random.nextInt(this.categoryService.countCategories()) + 1;
            result.add(this.categoryService.getCategoryById(bound1));
        }
        return result;
    }


    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;
        return this.authorService.findAuthorById((long) randomId);
    }
     public List<Book> getAllBooksAfter2000(){

         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
         LocalDate releaseDate = LocalDate.parse("31/12/2000", formatter);
        return bookRepo.findAllByReleaseDateAfter(releaseDate);
     }
     public  List<Book>findAllByReleaseDateBefore(){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
         LocalDate releaseDate = LocalDate.parse("01/01/1990", formatter);
         return bookRepo.findAllByReleaseDateBefore(releaseDate);
     }
     public List<Book> findByAuthor(){
        return bookRepo.findByAuthor();
     }
}

