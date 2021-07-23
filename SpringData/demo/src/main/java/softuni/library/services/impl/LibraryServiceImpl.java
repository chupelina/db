package softuni.library.services.impl;

import org.springframework.stereotype.Service;
import softuni.library.services.LibraryService;
@Service
public class LibraryServiceImpl implements LibraryService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readLibrariesFileContent() {
        return null;
    }

    @Override
    public String importLibraries() {
        return null;
    }
}
