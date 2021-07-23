package gson.demo.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.demo.utils.ValidatorUtil;
import gson.demo.utils.ValidatorUtilImpl;
import gson.demo.utils.XmlParser;
import gson.demo.utils.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl(validator());
    }
    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

}
