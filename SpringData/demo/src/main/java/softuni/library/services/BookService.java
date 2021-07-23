package softuni.library.services;

public interface BookService {
    boolean areImported();
    String readBooksFileContent();
    String importBooks();
}
