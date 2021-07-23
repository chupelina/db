package softuni.library.services.impl;

import org.springframework.stereotype.Service;
import softuni.library.services.CharacterService;
@Service
public class CharacterServiceImpl implements CharacterService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readCharactersFileContent() {
        return null;
    }

    @Override
    public String importCharacters() {
        return null;
    }

    @Override
    public String findCharactersInBookOrderedByLastNameDescendingThenByAge() {
        return null;
    }
}
