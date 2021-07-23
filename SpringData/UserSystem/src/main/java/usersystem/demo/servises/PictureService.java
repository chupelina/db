package usersystem.demo.servises;

import usersystem.demo.entities.Picture;

import java.io.IOException;

public interface PictureService {
    void seedPictures() throws IOException;
     int size();
     Picture getPictureById(int id);
}
