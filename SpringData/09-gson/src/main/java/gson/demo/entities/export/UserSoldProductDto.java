package gson.demo.entities.export;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class UserSoldProductDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductDto> soldProducts;

    public UserSoldProductDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
