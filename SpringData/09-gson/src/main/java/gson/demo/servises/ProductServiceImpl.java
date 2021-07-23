package gson.demo.servises;

import com.google.gson.Gson;
import gson.demo.entities.Category;
import gson.demo.entities.Product;
import gson.demo.entities.User;
import gson.demo.entities.dtos.seeds.ProductSeedDto;
import gson.demo.entities.export.ProductInRangeDto;
import gson.demo.repos.CategoryRepo;
import gson.demo.repos.ProductRepo;
import gson.demo.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final static String PATH = "src/main/resources/files/products.json";

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo,
                              UserRepo userRepo, CategoryRepo categoryRepo, ModelMapper modelMapper, Gson gson) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedData() throws IOException {
        String data = String.join("", Files.readAllLines(Path.of(PATH)));
        ProductSeedDto[] products = gson.fromJson(data, ProductSeedDto[].class);
        for (ProductSeedDto product : products) {
            Product product1 = modelMapper.map(product, Product.class);
            product1.setBayer(randomUser());
            product1.setSeller(randomSeller());
            product1.setCategories(randomCategory());
            productRepo.saveAndFlush(product1);
        }
    }

    @Override
    public String productsInRange() {
        List<Product> allProducts = productRepo.findAllByPriceBetweenOrderByPriceDesc(
                BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        Set<ProductInRangeDto> products = new HashSet<>();
        for (Product allProduct : allProducts) {
            ProductInRangeDto current = new ProductInRangeDto();
            current.setName(allProduct.getName());
            current.setPrice(allProduct.getPrice());

            User seller = allProduct.getSeller();
            if (seller!=null) {
                current.setSeller(allProduct.getSeller().getFirstName() + " "
                        + allProduct.getSeller().getLastName());
            }else{
                current.setSeller(null);
            }
            products.add(current);
        }
        return gson.toJson(products);
    }

    private Set<Category> randomCategory() {
        Random random = new Random();
        Set<Category> categories = new HashSet<>();
        int n = random.nextInt(1) + 1;
        for (int i = 0; i < n; i++) {
            int index = random.nextInt((int) categoryRepo.count()) + 1;
            categories.add(categoryRepo.findById((long) index).orElse(null));
        }
        return categories;
    }

    private User randomUser() {
        Random random = new Random();
        int index = random.nextInt((int) userRepo.count());
        Optional<User> user = userRepo.findById((long) index);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    private User randomSeller() {
        Random random = new Random();
        int index = random.nextInt((int) userRepo.count());
        Optional<User> user = userRepo.findById((long) index);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
}
