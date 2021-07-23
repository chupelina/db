package softuni.library.services.impl;

import org.springframework.stereotype.Service;
import softuni.library.services.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readAuthorsFileContent() {
        return null;
    }

    @Override
    public String importAuthors() {
        return null;
    }
}
