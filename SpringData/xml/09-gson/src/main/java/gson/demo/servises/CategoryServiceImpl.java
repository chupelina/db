package gson.demo.servises;

import gson.demo.dtos.export.CategoryProductsDto;
import gson.demo.dtos.export.CategoryProductsRootDto;
import gson.demo.dtos.imports.CategoryImportDto;
import gson.demo.dtos.imports.CategoryRootDto;
import gson.demo.entities.Category;
import gson.demo.repos.CategoryRepo;
import gson.demo.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private static String PATH_CATEGORY =
            "src/main/resources/files/categories.xml";

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo
            , ModelMapper modelMapper, XmlParser xmlParser) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedData() throws JAXBException {
        CategoryRootDto categoryRootDto = xmlParser
                .parseXml(CategoryRootDto.class, PATH_CATEGORY);
        for (CategoryImportDto category : categoryRootDto.getCategories()) {
            Category current = modelMapper.map(category, Category.class);
            categoryRepo.saveAndFlush(current);
        }

    }

    @Override
    public void infoAboutCategory() throws JAXBException {
        List<Category> all = categoryRepo.findAll();
        List<CategoryProductsDto> current = new ArrayList<>();

        for (Category category : all) {
            CategoryProductsDto categoryProductsDto = modelMapper.map(category,CategoryProductsDto.class);
            int size = category.getProducts().size();
            double total = category.getProducts().stream().mapToDouble(p->
                    Double.parseDouble(p.getPrice().toString())).sum();
            categoryProductsDto.setCount(size);
            categoryProductsDto.setTotalPrice(BigDecimal.valueOf(total));
            categoryProductsDto.setAveragePrice(total/(1.0*(size)));
            current.add(categoryProductsDto);
        }
        List<CategoryProductsDto> collect = current.stream().sorted((l, r) -> {
            int countR = r.getCount();
            int countL = l.getCount();
            int result = countR - countL;
            return result;
        }).collect(Collectors.toList());
        CategoryProductsRootDto next = new CategoryProductsRootDto();
        next.setProductsDtos(collect);
        String path = "src/main/resources/results/categories-by-products.xml";
        xmlParser.exportXml(next, CategoryProductsRootDto.class, path);
    }
}
