package softuni.library.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.library.util.ValidatorUtil;
import softuni.library.util.ValidatorUtilImpl;
import softuni.library.util.XmlParser;
import softuni.library.util.XmlParserImpl;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<>() {
                    @Override
                    public LocalDate deserialize(JsonElement json,
                                                 Type typeOfT,
                                                 JsonDeserializationContext context)
                            throws JsonParseException {
                        return LocalDate.parse(json.getAsString(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                }) .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json,
                                                     Type typeOfT,
                                                     JsonDeserializationContext context)
                            throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString(),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                })
                .create();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
    @Bean
    public ValidatorUtil validationUtil() {
        return new ValidatorUtilImpl(validator());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }


}
