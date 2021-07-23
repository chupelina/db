package softuni.library.models;

import org.w3c.dom.Text;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String description;
    private int edition;
    private String name;
    private LocalDate written;
    @ManyToOne
    private Author author;
    @OneToMany(mappedBy = "book")
    private Set<Character> characters;
    @ManyToMany(mappedBy = "books")
    private Set<Library> libraries;

    public Book() {
    }
}
