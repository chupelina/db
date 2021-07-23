package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.entities.Author;
import softuni.library.models.entities.Book;
import softuni.library.models.imports.BookDto;
import softuni.library.repositories.AuthorRepository;
import softuni.library.repositories.BookRepository;
import softuni.library.services.BookService;
import softuni.library.util.ValidatorUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtilImpl validatorUtil;
    private final static String PATH = "src/main/resources/files/json/books.json";
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(AuthorRepository authorRepository, Gson gson, ModelMapper modelMapper, ValidatorUtilImpl validatorUtil, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean areImported() {
        return bookRepository.count()>0;
    }

    @Override
    public String readBooksFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();
        String current= String.join("", Files.readAllLines(Path.of(PATH)));
        BookDto[] bookDtos = gson.fromJson(current, BookDto[].class);
        for (BookDto bookDto : bookDtos) {
            int edition = bookDto.getEdition();
            if(validatorUtil.isValid(bookDto) && (edition==1 || edition==2 ||edition ==3 ||
                    edition==4 || edition ==5) ){
                 Book book = modelMapper.map(bookDto, Book.class);
                Author author = authorRepository.findFirstById(bookDto.getAuthor());
                book.setAuthor(author);
                bookRepository.saveAndFlush(book);
                sb.append(String.format("Successfully imported Book: %s written in %s%n",
                        book.getName(), book.getWritten()));
            }else{
                sb.append("Invalid Book").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
