package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Character;
import softuni.library.models.imports.CharacterDto;
import softuni.library.models.imports.CharacterRootDto;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.CharacterRepository;
import softuni.library.services.CharacterService;
import softuni.library.util.ValidatorUtilImpl;
import softuni.library.util.XmlParserImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final XmlParserImpl xmlParser;
    private final CharacterRepository characterRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final ValidatorUtilImpl validatorUtil;
    private final static String PATH = "src/main/resources/files/xml/characters.xml";

    @Autowired
    public CharacterServiceImpl(XmlParserImpl xmlParser, CharacterRepository characterRepository, ModelMapper modelMapper, BookRepository bookRepository, ValidatorUtilImpl validatorUtil) {
        this.xmlParser = xmlParser;
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public boolean areImported() {
        return characterRepository.count()>0;
    }

    @Override
    public String readCharactersFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importCharacters() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        CharacterRootDto characterRootDto =xmlParser.parseXml(CharacterRootDto.class, PATH);
        for (CharacterDto characterDto : characterRootDto.getCharacters()) {
            int age = characterDto.getAge();
            String middleName = characterDto.getMiddleName();
            if(validatorUtil.isValid(characterDto)&& age>=10 && age<=66 && middleName.length()==1 ){
                Character character  = modelMapper.map(characterDto, Character.class);
                LocalDate current = LocalDate.parse(characterDto.getBirthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Book book = bookRepository.findFirstById(characterDto.getBook().getId());
                character.setBook(book);
                character.setMiddleName(middleName);
                character.setBirthday(current);
                characterRepository.saveAndFlush(character);
                sb.append(String.format("Successfully imported Character %s %s %s, age %d, in book %s%n",
                        character.getFirstName(), character.getMiddleName(), character.getLastName(),
                        character.getAge(), character.getBook().getName()));
            }else{
              sb.append("Invalid Character").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String findCharactersInBookOrderedByLastNameDescendingThenByAge() {
        StringBuilder sb = new StringBuilder();
        List<Character> characters = characterRepository.findAll();
        for (Character character : characters) {
            sb.append(String.format("Character name %s %s %s, age %d, in book %s%n",
                    character.getLastName(), character.getMiddleName(), character.getLastName()
            , character.getAge(), character.getBook().getName()));
        }
        return sb.toString();
    }
}
