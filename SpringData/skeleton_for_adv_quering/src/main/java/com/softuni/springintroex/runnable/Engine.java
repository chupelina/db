package com.softuni.springintroex.runnable;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Engine implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public Engine(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
        System.out.println("Hi, my name is Josh and I'll help you with this, " +
                "if you wanna get rid of me press 0.");
        System.out.println("Enter the task number:");
        int input = Integer.parseInt(scan.nextLine());

        while (input != 0) {
            switch (input) {
                case 1:
                    System.out.println("Try me. Enter an age restriction");
                    String current = scan.nextLine();
                    if (AgeRestriction.isContaining(current)) {
                        this.bookService.printAllBooksByAgeRestriction("adult");
                    } else {
                        System.out.println("This was incorrect. I am watching you.");
                    }
                    break;
                case 2:
                    System.out.println("That are the books with golden edition type and " +
                            "more than 5000 copies:");
                    this.bookService.findAllByEditionTypeAndCopies("gold", 5000);
                    break;
                case 3:
                    System.out.println("Please enter 2 numbers separated with space:");
                    String[] currents = scan.nextLine().split("\\s+");
                    int first = Integer.parseInt(currents[0]);
                    int second = Integer.parseInt(currents[1]);
                    if (first < 0 || second < 0 || first > second) {
                        System.out.println("I told you I am watching!");
                    } else {
                        this.bookService.findAllWhitPriceBetween(first, second);
                    }
                    break;
                case 4:
                    System.out.println("Enter the year that you want to exclude:");
                    current= scan.nextLine();
                    this.bookService.findAllWhitReleasedYearNotIn(current);
                    break;
                case 5:
                    System.out.println("Please write the release date before witch you need" +
                            " like (dd-mm-yyyy):");
                    current = scan.nextLine();
                    this.bookService.findAllWhitReleasedDateBefore(current);
                    break;
                case 6:
                    System.out.println("Give me the ending of the authors First name :");
                    current = scan.nextLine();
                    this.authorService.findAllAuthorsWhitNameEndsWith(current);
                    break;
                case 7:
                    System.out.println("Type some letters for me, and I will return all " +
                            "the books, whose titles are containing them");
                    current = scan.nextLine();
                    this.bookService.findAllBookTitlesContaining(current);
                    break;
                case 8:
                    System.out.println("I'm good at this game. I would like to find for " +
                            "you all of the books and authors, if you give me " +
                            "the starting letters of authors last name:");
                    current=scan.nextLine();
                    this.bookService.findAllBooksWithAuthor(current);
                    break;
                case 9:
                    System.out.println("You can not get rid of me, type a number and I will " +
                            "return the number of books whose length is more than it");
                    current=scan.nextLine();
                    this.bookService.findAllBooksWithTitleMoreThan(Integer.parseInt(current));
                    break;
                case 10:
                    System.out.println("Those are all authors with their count of copies:");
                    this.authorService.printAllAuthorsByBookCopies();
                    break;
                case 11:
                    System.out.println("Give me a title and I will tell you everything I know");
                    current = scan.nextLine();
                    this.bookService.findTheRightBook(current);
                    break;
                case 12:
                    System.out.println("Type the copies and the date of the books to increased " +
                            "(the date in format 12 Oct 2005):");
                    currents= scan.nextLine().split("\\s+",2);
                    this.bookService.increaseCopies(currents[0], currents[1]);
                    break;
                case 13:
                    System.out.println("I do not want to delete anything but only for you." +
                            " Type the count of the copies:");
                    current = scan.nextLine();
                    this.bookService.deleteAllBooksWithCopiesLessThen(Integer.parseInt(current));
                    break;
                case 14:
                    System.out.println("This is not a procedure. Sorry but I found another " +
                            "way. Type first and last name of the author:");
                    currents=scan.nextLine().split("\\s+");
                    this.authorService.getAllAuthorsWithBooks(currents[0], currents[1]);
                    break;
            }
            System.out.println("=".repeat(150));
            System.out.println("And the task you want me to chek for you is" +
                    "(if you do not press 0):");
            input = Integer.parseInt(scan.nextLine());
        }

        System.out.println("Thanks for your time :)");
        System.out.println("P.P. See you soon, Josh");
    }
}
