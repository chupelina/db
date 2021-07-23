package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Library;
import softuni.library.models.imports.CharacterRootDto;
import softuni.library.models.imports.LibraryDto;
import softuni.library.models.imports.LibraryRootDto;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.LibraryRepository;
import softuni.library.services.LibraryService;
import softuni.library.util.ValidatorUtilImpl;
import softuni.library.util.XmlParserImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final XmlParserImpl xmlParser;
    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final ValidatorUtilImpl validatorUtil;
    private final static String PATH = "src/main/resources/files/xml/libraries.xml";


    @Autowired
    public LibraryServiceImpl(XmlParserImpl xmlParser, LibraryRepository libraryRepository, ModelMapper modelMapper, BookRepository bookRepository, ValidatorUtilImpl validatorUtil) {
        this.xmlParser = xmlParser;
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return libraryRepository.count() > 0;
    }

    @Override
    public String readLibrariesFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importLibraries() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        LibraryRootDto libraryRootDto = xmlParser.parseXml(LibraryRootDto.class, PATH);
        for (LibraryDto libraryDto : libraryRootDto.getLibraries()) {
            Optional<Library> firstByName = libraryRepository.findFirstByName(libraryDto.getName());
            int reating = libraryDto.getReating();
            if (validatorUtil.isValid(libraryDto)  && firstByName.isEmpty() &&(
                    reating==1 || reating ==2 || reating==3 || reating ==4 || reating==5
                    || reating==6 || reating==7 || reating==8 || reating ==9 || reating==10
                    )) {
                Library library = modelMapper.map(libraryDto, Library.class);
                library.setRating(reating);
                Set<Book> books = new HashSet<>();
                Book book = bookRepository.findFirstById(libraryDto.getBook().getId());
                books.add(book);
                library.setBooks(books);
                libraryRepository.saveAndFlush(library);
                sb.append(String.format("Successfully added Library: %s - %s%n"
                , library.getName(), library.getLocation()));
            } else {
                sb.append("Invalid Library").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
