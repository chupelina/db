package softuni.library.models.imports;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.library.models.entities.Author;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookDto {
    @Expose
    private String description;
    @Expose
    private int edition;
    @Expose
    private String name;
    @Expose
    private LocalDate written;
    @Expose
    private int author;

    public BookDto() {
    }
@Length(min =100)
@Lob
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
@NotNull
@Length(min =5)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
@NotNull
    public LocalDate getWritten() {
        return written;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
