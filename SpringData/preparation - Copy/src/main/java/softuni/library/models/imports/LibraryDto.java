package softuni.library.models.imports;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryDto {
    @XmlElement
    private String name;
    @XmlElement
    private String location;
    @XmlElement(name = "rating")
    private int reating;
    @XmlElement
    private BookCharDto book;

    public LibraryDto() {
    }
@Length(min = 3)
@NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Length(min =5)
    @NotNull
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReating() {
        return reating;
    }

    public void setReating(int reating) {
        this.reating = reating;
    }

    public BookCharDto getBook() {
        return book;
    }

    public void setBook(BookCharDto book) {
        this.book = book;
    }
}
