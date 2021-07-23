package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Picture;
import softuni.exam.models.imports.PictureSeedDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepo;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final CarRepository carRepo;
    private final ValidationUtil validationUtil;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepo, ModelMapper modelMapper, Gson gson, CarRepository carRepo, ValidationUtil validationUtil) {
        this.pictureRepo = pictureRepo;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.carRepo = carRepo;
        this.validationUtil = validationUtil;

    }


    @Override
    public boolean areImported() {
        return pictureRepo.count() >0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        StringBuilder st = new StringBuilder();
        String path = "src/main/resources/files/json/pictures.json";
        String current = String.join("", Files.readAllLines(Path.of(path)));
        PictureSeedDto[] pictures = gson.fromJson(current, PictureSeedDto[].class);
        for (PictureSeedDto picture : pictures) {
            Picture one = modelMapper.map(picture, Picture.class);
            one.setCar(carRepo.findById(picture.getCar()).get());
            if (validationUtil.isValid(one)) {
                st.append(String.format("Successfully import picture - %s%n", one.getName()));
                pictureRepo.saveAndFlush(one);
            } else {
                st.append("Invalid picture").append(System.lineSeparator());
            }
        }
        return st.toString();
    }

    @Override
    public String importPictures() throws IOException {
        return null;
    }
}
