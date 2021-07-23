package gson.demo.servises;

import com.google.gson.Gson;
import gson.demo.entities.Product;
import gson.demo.entities.User;
import gson.demo.entities.dtos.seeds.UserSeedDto;
import gson.demo.entities.export.ProductDto;
import gson.demo.entities.export.UserSoldProductDto;
import gson.demo.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final static String PATH = "src/main/resources/files/users.json";

    @Autowired
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, Gson gson) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedData() throws IOException {
        String current = String.join("", Files.readAllLines(Path.of(PATH)));
        UserSeedDto[] users = gson.fromJson(current, UserSeedDto[].class);
        for (UserSeedDto user : users) {
            User person = modelMapper.map(user, User.class);
            userRepo.saveAndFlush(person);
        }

    }

    @Override
    public String findAllWithProduct() {
        List<User> users = userRepo.findAllBySold();
        List<UserSoldProductDto> output = new ArrayList<>();
        for (User user : users) {
            UserSoldProductDto userSoldProductDto = modelMapper.map(user, UserSoldProductDto.class);
            Set<Product> sold = user.getSold();
            Set<ProductDto> productDtos = new HashSet<>();
            for (Product product : sold) {
                ProductDto productDto = modelMapper.map(product, ProductDto.class);
                if (product.getBayer().getFirstName() == null) {
                    productDto.setBayerFirstName(null);

                } else {
                    productDto.setBayerFirstName(product.getBayer().getFirstName());
                }
                if (product.getBayer().getLastName() != null) {
                    productDto.setBayerLastName(product.getBayer().getLastName());
                } else {
                    productDto.setBayerLastName(null);
                }
                productDtos.add(productDto);
            }
            userSoldProductDto.setSoldProducts(productDtos);
            output.add(userSoldProductDto);
        }
        return gson.toJson(output);
    }
}
