package gson.demo.servises;

import com.google.gson.Gson;
import gson.demo.entities.Category;
import gson.demo.entities.dtos.seeds.CategorySeedDto;
import gson.demo.repos.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private static String PATH_CATEGORY =
            "src/main/resources/files/categories.json";

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo
            , ModelMapper modelMapper, Gson gson) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedData() throws IOException {
        String current = String.join("", Files.readAllLines
                (Path.of(PATH_CATEGORY)));
        CategorySeedDto[] categories = gson.fromJson(current, CategorySeedDto[].class);
        for (CategorySeedDto category : categories) {
            Category category1 = modelMapper.map(category, Category.class);
            categoryRepo.saveAndFlush(category1);
        }
    }
}
