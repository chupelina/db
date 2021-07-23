package softuni.library.models.imports;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterDto {
    @XmlElement
    private int age;
    @XmlElement
    private String birthday;
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement(name = "middle-name")
    private String middleName;
    @XmlElement
    private String role;
    @XmlElement(name = "book")
    private BookCharDto book;

    public CharacterDto() {
    }

    @NotNull
    @Positive
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotNull
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Length(min = 3)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 3)
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Length(min = 1, max = 1)
    public String getMiddleName() {
        return middleName;
    }


    @NotNull
    @Length(min = 5)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BookCharDto getBook() {
        return book;
    }

    public void setBook(BookCharDto book) {
        this.book = book;
    }
}
