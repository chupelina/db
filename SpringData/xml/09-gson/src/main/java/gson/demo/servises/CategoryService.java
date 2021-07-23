package gson.demo.servises;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CategoryService {
    void seedData() throws JAXBException;
    void infoAboutCategory() throws JAXBException;
}
