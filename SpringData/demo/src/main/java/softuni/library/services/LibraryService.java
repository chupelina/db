package softuni.library.services;

public interface LibraryService {
    boolean areImported();
    String readLibrariesFileContent();
    String importLibraries() ;
}
