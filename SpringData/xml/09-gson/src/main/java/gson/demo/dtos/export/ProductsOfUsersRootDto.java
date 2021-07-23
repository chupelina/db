package gson.demo.dtos.export;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsOfUsersRootDto {
    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "product")
    private List<ProductsOfUsersDto> products;

    public ProductsOfUsersRootDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductsOfUsersDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsOfUsersDto> products) {
        this.products = products;
    }
}
