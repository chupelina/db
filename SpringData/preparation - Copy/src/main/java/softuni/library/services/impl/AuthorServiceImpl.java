package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import softuni.library.models.entities.Author;
import softuni.library.models.imports.AuthorDto;
import softuni.library.repositories.AuthorRepository;
import softuni.library.services.AuthorService;
import softuni.library.util.ValidatorUtil;
import softuni.library.util.ValidatorUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtilImpl validatorUtil;
    private final static String PATH = "src/main/resources/files/json/authors.json";

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, Gson gson, ModelMapper modelMapper,ValidatorUtilImpl validatorUtil) {
        this.authorRepository = authorRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return authorRepository.count()>0;
    }

    @Override
    public String readAuthorsFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importAuthors() throws IOException {
        StringBuilder sb = new StringBuilder();
        String current= String.join("", Files.readAllLines(Path.of(PATH)));
        AuthorDto[] authors =gson.fromJson(current, AuthorDto[].class);
        for (AuthorDto authorDto : authors) {
            Optional<Author> isFound = authorRepository.findFirstByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName());
            if(validatorUtil.isValid(authorDto) && isFound.isEmpty()){
                Author author = modelMapper.map(authorDto, Author.class);
                authorRepository.saveAndFlush(author);
                sb.append(String.format("Successfully imported Author: %s %s - %s%n",
                        author.getFirstName(), author.getLastName(), author.getBirthTown()));
            }else{
        sb.append("Invalid Author").append(System.lineSeparator());
            }
        }


        return sb.toString();
    }
}
