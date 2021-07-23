package gson.demo.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductsRootDto {
    @XmlElement(name = "category")
    private List<CategoryProductsDto> productsDtos;

    public CategoryProductsRootDto() {
    }

    public List<CategoryProductsDto> getProductsDtos() {
        return productsDtos;
    }

    public void setProductsDtos(List<CategoryProductsDto> productsDtos) {
        this.productsDtos = productsDtos;
    }
}
