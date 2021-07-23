package gson.demo.dtos.export;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSalesDto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElement(name = "sold-products")
    private SoldProductsRootDto product;

    public UserWithSalesDto() {
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

    public SoldProductsRootDto getProduct() {
        return product;
    }

    public void setProduct(SoldProductsRootDto product) {
        this.product = product;
    }
}
