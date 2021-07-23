package gson.demo.servises;

import gson.demo.dtos.export.ProductsOfUsersDto;
import gson.demo.dtos.export.ProductsOfUsersRootDto;
import gson.demo.dtos.export.UserProductsDto;
import gson.demo.dtos.export.UserProductsRootDto;
import gson.demo.dtos.imports.UserImportDto;
import gson.demo.dtos.imports.UserRootDto;
import gson.demo.entities.Product;
import gson.demo.entities.User;
import gson.demo.repos.UserRepo;
import gson.demo.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final static String PATH = "src/main/resources/files/users.xml";
    private final XmlParser xmlParser;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, XmlParser xmlParser) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedData() throws JAXBException {
        UserRootDto userRootDto = new UserRootDto();

        UserRootDto dto = xmlParser.parseXml(UserRootDto.class, PATH);
        for (UserImportDto user : dto.getUsers()) {
            User current = modelMapper.map(user, User.class);
            userRepo.saveAndFlush(current);
        }

    }

    @Override
    public void findTotalUsersAndProducts() throws JAXBException {
        String path = "src/main/resources/results/users-and-products.xml";
        List<User> allSellers = userRepo.findAllBySold();
        List<UserProductsDto> current = new ArrayList<>();
        UserProductsDto currentUser = new UserProductsDto();
        for (int i = 0; i < allSellers.size(); i++) {
            UserProductsDto userProductsDto = modelMapper.map(allSellers.get(i), UserProductsDto.class);
            List<ProductsOfUsersDto> productsOfUsersDtos = new ArrayList<>();
            for (Product product : allSellers.get(i).getSold()) {
                ProductsOfUsersDto currentProduct = modelMapper.map(product, ProductsOfUsersDto.class);
                productsOfUsersDtos.add(currentProduct);
            }
            ProductsOfUsersRootDto next = new ProductsOfUsersRootDto();
            next.setProducts(productsOfUsersDtos);
            next.setCount(productsOfUsersDtos.size());
            userProductsDto.setProducts(next);
            current.add(userProductsDto);
        }
        current = current.stream().sorted((l, r) -> {
            int countL = l.getProducts().getCount();
            int countR = r.getProducts().getCount();
            int result = countR - countL;
            return result;
        }).collect(Collectors.toList());
        UserProductsRootDto output = new UserProductsRootDto();
        output.setUsers(current);
        output.setCount(current.size());
        xmlParser.exportXml(output, UserProductsRootDto.class, path);
    }
}
