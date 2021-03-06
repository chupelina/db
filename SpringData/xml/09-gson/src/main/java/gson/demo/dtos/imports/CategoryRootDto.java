package gson.demo.dtos.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootDto {
    @XmlElement(name = "category")
    private List<CategoryImportDto> categories;

    public CategoryRootDto() {
    }

    public List<CategoryImportDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryImportDto> categories) {
        this.categories = categories;
    }
}
