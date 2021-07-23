package gson.demo.servises;

import gson.demo.dtos.export.*;
import gson.demo.dtos.imports.ProductImportDto;
import gson.demo.dtos.imports.ProductRootDto;
import gson.demo.entities.Category;
import gson.demo.entities.Product;
import gson.demo.entities.User;
import gson.demo.repos.CategoryRepo;
import gson.demo.repos.ProductRepo;
import gson.demo.repos.UserRepo;
import gson.demo.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final static String PATH = "src/main/resources/files/products.xml";

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, UserRepo userRepo,
                              CategoryRepo categoryRepo, ModelMapper modelMapper, XmlParser xmlParser) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;

        this.xmlParser = xmlParser;
    }

    @Override
    public void seedData() throws JAXBException {
        ProductRootDto productRootDto = xmlParser.parseXml(ProductRootDto.class, PATH);
        for (ProductImportDto product : productRootDto.getProducts()) {
            Product current = modelMapper.map(product, Product.class);
            current.setSeller(randomSeller());
            current.setBuyer(randomBayer());
            current.setCategories(randomCategory());
            productRepo.saveAndFlush(current);
        }
    }

    @Override
    public void productsInRange() throws JAXBException {
        String path = "src/main/resources/results/products-in-range.xml";
        List<Product> products = productRepo.findByPrice();
        List<ProductInRangeDto> output = new LinkedList<>();

        for (Product product : products) {
            ProductInRangeDto current = modelMapper.map(product, ProductInRangeDto.class);
            if (product.getSeller() != null) {
                String fName = product.getSeller().getFirstName();
                String lName = product.getSeller().getLastName();
                current.setSellerFullName(fName + " " + lName);
            }
            output.add(current);
        }
        ProductInRangeRootDto productInRangeRootDto = new ProductInRangeRootDto();
        productInRangeRootDto.setProducts(output);
        xmlParser.exportXml(productInRangeRootDto, ProductInRangeRootDto.class, path);

    }

    @Override
    public void findAllSellers() throws JAXBException {
        String path = "src/main/resources/results/users-sold-products.xml";
        List<Product> products = productRepo.findAllSellers();
        List<SoldProductsDto> current = new ArrayList<>();
        List<UserWithSalesDto> users = new ArrayList<>();
        User user = products.get(0).getSeller();
        for (int i = 0; i < products.size(); i++) {
            SoldProductsDto one = modelMapper.map(products.get(i), SoldProductsDto.class);
            String firstName = products.get(i).getBuyer().getFirstName();
            String lastName = products.get(i).getBuyer().getLastName();
            one.setBuyerFirstName(firstName);
            one.setBuyerLastName(lastName);


            UserWithSalesDto userWithSalesDto = new UserWithSalesDto();
            if (products.get(i).getSeller().getId().equals(user.getId())) {
                current.add(one);
            } else {
                user = products.get(i).getSeller();
                userWithSalesDto = modelMapper.map(user, UserWithSalesDto.class);
                SoldProductsRootDto soldProductsRootDto = new SoldProductsRootDto();
                soldProductsRootDto.setProducts(current);
                userWithSalesDto.setProduct(soldProductsRootDto);
                current.clear();
                users.add(userWithSalesDto);
                i--;
            }
        }
        UserWithSalesRootDto userWithSalesRootDto = new UserWithSalesRootDto();
        userWithSalesRootDto.setUsers(users);
        xmlParser.exportXml(userWithSalesRootDto, UserWithSalesRootDto.class, path);
    }

    private Set<Category> randomCategory() {
        Random random = new Random();
        Set<Category> categories = new HashSet<>();
        int n = random.nextInt(2) + 1;
        for (int i = 0; i < n; i++) {
            int index = random.nextInt((int) categoryRepo.count()) + 1;
            categories.add(categoryRepo.findById((long) index).orElse(null));
        }
        return categories;
    }

    private User randomBayer() {
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
