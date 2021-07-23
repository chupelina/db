package softuni.library.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String birthTown;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "author")
    private Set<Book> books;

}
