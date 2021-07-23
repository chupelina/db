package usersystem.demo.servises.impl;

import org.springframework.stereotype.Service;
import usersystem.demo.entities.Picture;
import usersystem.demo.repositories.PictureRepo;
import usersystem.demo.servises.PictureService;
import usersystem.demo.units.FileUnit;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepo pictureRepo;
    private final FileUnit fileUnit;

    public PictureServiceImpl(PictureRepo pictureRepo, FileUnit fileUnit) {
        this.pictureRepo = pictureRepo;
        this.fileUnit = fileUnit;
    }

    @Override
    public void seedPictures() throws IOException {
        if(pictureRepo.count()!=0){
            return;
        }
      String path = "src/main/resources/users/picNames.txt";
      String[] pictures = fileUnit.readFile(path);
        Arrays.stream(pictures).forEach(p->{
            Picture picture = new Picture(p);
            pictureRepo.saveAndFlush(picture);
        });
    }

    @Override
    public int size() {
        return (int) this.pictureRepo.count();
    }

    public Picture getPictureById(int id) {
       return pictureRepo.findById(id).orElse(pictureRepo.getOne(4));

    }

}
