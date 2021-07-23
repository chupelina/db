package com.example.springlab.cintrillers;

import com.example.springlab.entities.Author;
import com.example.springlab.entities.Book;
import com.example.springlab.servises.AuthorService;
import com.example.springlab.servises.BookService;
import com.example.springlab.servises.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        //seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        Scanner scan = new Scanner(System.in);
        System.out.println("What you want to check? If you do not press 0.");
        int input = Integer.parseInt(scan.nextLine());
        while (input != 0) {
            if (input == 1) {
                System.out.println("Those are all books after 2000->");
                List<Book> books = bookService.getAllBooksAfter2000();
                books.forEach(b -> System.out.println(b.getTitle()));
            } else if (input == 2) {
                System.out.println("Those are all authors, whose books are release before 1990->");
                List<Author> authors = authorService.findAll();
                authors.forEach(a -> System.out.println(a.getFirstName() + " "
                        + a.getLastName()));
            } else if (input == 3) {
                System.out.println("Those are all authors with they count of books->");
                authorService.findAllByCountBooks()
                        .forEach(a -> System.out.println(a.getFirstName() + " "
                                + a.getLastName() + " " + a.getBooks().size()));
            } else if (input == 4) {
                System.out.println("All of books written by George Powell");
                bookService.findByAuthor().forEach(b -> {
                    System.out.printf("%s %s %d%n",
                            b.getTitle(), b.getReleaseDate().toString(),
                            b.getCopies());
                });
            }
            System.out.println("What you want to check? If you do not press 0.");
            input = Integer.parseInt(scan.nextLine());
        }
        System.out.println("Thanks for your time :)");
    }
}
