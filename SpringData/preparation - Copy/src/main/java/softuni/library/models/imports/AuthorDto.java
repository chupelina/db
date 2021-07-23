package softuni.library.models.imports;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

public class AuthorDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String birthTown;

    public AuthorDto() {
    }

    @NotNull
    @Length(min = 2)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Length(min = 2)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotNull
    @Length(min =2 , max =12)
    public String getBirthTown() {
        return birthTown;
    }

    public void setBirthTown(String birthTown) {
        this.birthTown = birthTown;
    }
}
