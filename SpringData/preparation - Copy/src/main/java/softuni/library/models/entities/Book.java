package softuni.library.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @Lob
    private String description;
    private int edition;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate written;
    @ManyToOne
    private Author author;
    @OneToMany(mappedBy = "book")
    private Set<Character> characters;
    @ManyToMany(mappedBy = "books")
    private Set<Library> libraries;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getWritten() {
        return written;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Set<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Library> libraries) {
        this.libraries = libraries;
    }
}
