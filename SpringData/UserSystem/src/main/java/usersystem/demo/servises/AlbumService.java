package usersystem.demo.servises;

import usersystem.demo.entities.Album;

import java.io.IOException;

public interface AlbumService {
    void seedAlbums() throws IOException;
    int size();
    Album getById(int id);
}
