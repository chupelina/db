package softuni.library.services.impl;

import org.springframework.stereotype.Service;
import softuni.library.services.BookService;

import javax.persistence.SecondaryTable;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readBooksFileContent() {
        return null;
    }

    @Override
    public String importBooks() {
        return null;
    }
}
