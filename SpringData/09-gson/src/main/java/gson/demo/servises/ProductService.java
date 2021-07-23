package gson.demo.servises;

import java.io.IOException;

public interface ProductService {
    void seedData() throws IOException;
    String productsInRange();
}
