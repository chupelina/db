package gson.demo.servises;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ProductService {
    void seedData() throws JAXBException;
    void productsInRange() throws JAXBException;
    void findAllSellers() throws JAXBException;

}
