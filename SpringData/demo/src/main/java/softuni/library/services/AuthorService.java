package softuni.library.services;

public interface AuthorService {
    boolean areImported();
    String readAuthorsFileContent();
    String importAuthors();
}
