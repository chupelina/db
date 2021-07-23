package softuni.library.services;

public interface CharacterService {
    boolean areImported();
    String readCharactersFileContent();
    String importCharacters();
    String findCharactersInBookOrderedByLastNameDescendingThenByAge();
}
