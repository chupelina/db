package gson.demo.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsRootDto {
    @XmlElement(name = "product")
    private List<SoldProductsDto> products;

    public SoldProductsRootDto() {
    }

    public List<SoldProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<SoldProductsDto> products) {
        this.products = products;
    }
}
