package gson.demo.servises;

import java.io.IOException;

public interface UserService {
    void seedData() throws IOException;
    String findAllWithProduct();
}
