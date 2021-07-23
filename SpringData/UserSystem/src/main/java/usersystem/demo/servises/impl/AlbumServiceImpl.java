package usersystem.demo.servises.impl;

import org.springframework.stereotype.Service;
import usersystem.demo.entities.Album;
import usersystem.demo.entities.Picture;
import usersystem.demo.repositories.AlbumRepo;
import usersystem.demo.servises.AlbumService;
import usersystem.demo.servises.PictureService;
import usersystem.demo.units.FileUnit;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final FileUnit fileUnit;
    private final AlbumRepo albumRepo;
    private final PictureService pictureService;

    public AlbumServiceImpl(FileUnit fileUnit, AlbumRepo albumRepo, PictureService pictureService) {
        this.fileUnit = fileUnit;
        this.albumRepo = albumRepo;
        this.pictureService = pictureService;
    }

    @Override
    public void seedAlbums() throws IOException {
        if (albumRepo.count() != 0) {
            return;
        }
        String path = "src/main/resources/users/albums.txt";
        String[] albums = this.fileUnit.readFile(path);
        Arrays.stream(albums).forEach(a -> {
            String[] current = a.split("\\s+", 3);
            String color = current[0];
            Boolean isVisible = current[1].equals("true");
            String name = current[2];
            Set<Picture> pictures = getPictures();

            Album album = new Album(color, isVisible, name, pictures);
            albumRepo.saveAndFlush(album);
        });
    }

    @Override
    public int size() {
        return (int) this.albumRepo.count();
    }

    @Override
    public Album getById(int id) {
        return this.albumRepo.findById(id).orElse(albumRepo.getOne(1));
    }

    private Set<Picture> getPictures() {
        Set<Picture> pictures = new LinkedHashSet<>();
        Random random = new Random();
        int index = random.nextInt(2) + 1;
        for (int i = 0; i < index; i++) {
            int index1 = random.nextInt(pictureService.size()) + 1;
            pictures.add(pictureService.getPictureById(index1));
        }
        return pictures;
    }
}
