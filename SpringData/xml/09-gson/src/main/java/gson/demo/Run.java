package gson.demo;

import gson.demo.servises.CategoryService;
import gson.demo.servises.ProductService;
import gson.demo.servises.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);
        System.out.println("If you want to seed the data press 0 else some other number");
        int n = Integer.parseInt(scan.nextLine());
        if (n == 0) {
            userService.seedData();
            categoryService.seedData();
            productService.seedData();
        }
        productService.productsInRange();
        productService.findAllSellers();
        categoryService.infoAboutCategory();
        userService.findTotalUsersAndProducts();
        String path = "src/main/resources/results";
        System.out.println("The output is in: "+path+" thanks for your time :)");
    }
}
