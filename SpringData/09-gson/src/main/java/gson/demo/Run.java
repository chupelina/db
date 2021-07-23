package gson.demo;

import gson.demo.servises.CategoryService;
import gson.demo.servises.ProductService;
import gson.demo.servises.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Run implements CommandLineRunner {
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public Run(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.seedData();
//        categoryService.seedData();
//        productService.seedData();
//        System.out.println(productService.productsInRange());
        System.out.println(userService.findAllWithProduct());

    }
}
