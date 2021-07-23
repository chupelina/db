package softuni.library.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String location;
    private String name;
    private int rating;
    @ManyToMany
    private Set<Book> books;
}
