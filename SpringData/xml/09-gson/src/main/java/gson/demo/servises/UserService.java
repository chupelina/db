package gson.demo.servises;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface UserService {
    void seedData() throws JAXBException;
    void findTotalUsersAndProducts() throws JAXBException;
}
