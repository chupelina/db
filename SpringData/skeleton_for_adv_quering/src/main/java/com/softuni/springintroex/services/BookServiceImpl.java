package com.softuni.springintroex.services;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repozitory.AuthorRepo;
import com.softuni.springintroex.repozitory.BookRepo;
import com.softuni.springintroex.repozitory.CategoryRepo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private final FileUtil fileUtil;
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepo bookRepo, AuthorRepo authorRepo, CategoryRepo categoryRepo) {
        this.fileUtil = fileUtil;
        this.bookRepo = bookRepo;

        this.authorRepo = authorRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    @Transactional
    public void seedBooks() throws IOException {
        if (bookRepo.count() != 0) {
            return;
        }
        String[] lines = this.fileUtil.
                readFileContent(GlobalConstants.BOOKS_FILE_PATH);
        Random random = new Random();
        for (String line : lines) {
            String[] data = line.split("\\s+", 6);
            long authorIndex = random.nextInt((int) this.authorRepo.count()) + 1;
            Author author = this.authorRepo.findById(authorIndex).get();
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(data[1], formatter);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();

            String title = data[5];

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(localDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(getRandomCategories());
            this.bookRepo.saveAndFlush(book);
        }

    }

    @Override
    public void printAllBooksByAgeRestriction(String restriction) {
        this.bookRepo.findByAgeRestrictionOrderById
                (AgeRestriction.valueOf(restriction.toUpperCase()))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void findAllByEditionTypeAndCopies(String editionType, int copies) {
        this.bookRepo.findAllByEditionTypeAndCopiesLessThan(
                EditionType.valueOf(editionType.toUpperCase()), copies
        ).forEach(b -> System.out.println(b.getTitle()));

    }

    @Override
    public void findAllWhitPriceBetween(int min, int max) {
        BigDecimal bigDecimal = BigDecimal.valueOf(min);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(max);
        bookRepo.findAllByPriceLessThanOrPriceGreaterThan(bigDecimal, bigDecimal1)
                .forEach(b -> System.out.println(
                        b.getTitle() + " - $" + b.getPrice()));
    }

    @Override
    public void findAllWhitReleasedYearNotIn(String year) {
        bookRepo.findAllByReleaseDateNotIn(year).forEach(b ->
                System.out.println(b.getTitle())
        );

    }

    @Override
    public void findAllWhitReleasedDateBefore(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        bookRepo.findAllByReleaseDateBefore(localDate).forEach(
                b -> System.out.printf("%-30.30s %-10.10s %5.2f%n",
                        b.getTitle(), b.getEditionType(),
                        b.getPrice())
        );
    }

    @Override
    public void findAllBookTitlesContaining(String text) {
        List<Book> books = new ArrayList<>(bookRepo.findAllByTitleContaining(text));
        if (books.isEmpty()) {
            System.out.println("Sorry bro I could not find anything");
        } else {
            books.forEach(
                    b -> System.out.println(b.getTitle()));
        }
    }

    @Override
    public void findAllBooksWithAuthor(String text) {
        Set<Author> authorList = authorRepo.findAllByLastNameStartingWith(text);
        if (authorList.isEmpty()) {
            System.out.println("Sorry try again ;(");
        }
        for (Author author : authorList) {
            bookRepo.findAllByAuthor(author).forEach(book ->
                    System.out.printf("%s (%s %s)%n",
                            book.getTitle(), author.getFirstName(),
                            author.getLastName()));
        }
    }

    @Override
    public void findAllBooksWithTitleMoreThan(int symbols) {
        long count = bookRepo.findAllByTitle(symbols).stream().count();
        if (count == 0) {
            System.out.println("There is not such book");
        } else if (count == 1) {
            System.out.printf("There is %d book with longer title than %d symbols%n"
                    , count, symbols);
        } else {
            System.out.printf("There are %d books with longer title than %d symbols%n"
                    , count, symbols);
        }
    }

    @Override
    public void findAllBookCopiesWithAuthor() {

    }

    @Override
    public void findTheRightBook(String title) {
        Book book = bookRepo.findBookByTitleEquals(title);
        try {
            System.out.printf("%s %s %s %.2f%n",
                    book.getTitle(), book.getEditionType()
                    , book.getAgeRestriction(), book.getPrice());
        } catch (NullPointerException ex) {
            System.out.println("There is no such book!");
        }
    }

    @Override
    public void increaseCopies(String count, String date) {
        Locale.setDefault(Locale.FRANCE);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.US);
        LocalDate localDate = LocalDate.parse(date, dtf);
        int copies = Integer.parseInt(count);
        int n = bookRepo.increaseBooksCopies(copies, localDate);
        System.out.printf("%d books are released after %s, " +
                        "so total of %d book copies were added%n",
                n, date, n * copies);
    }

    @Override
    public void deleteAllBooksWithCopiesLessThen(int count) {
        int n = bookRepo.deleteAllByCopiesLessThan(count);
        if (n == 0) {
            System.out.println("There os nothing to delete");
        } else {
            System.out.printf("There are %d deleted books.%n", n);
        }
    }

    Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            long catIndex = random.nextInt((int) categoryRepo.count()) + 1;
            Category category = categoryRepo.findById(catIndex).get();
            categories.add(category);
        }
        return categories;
    }
}
