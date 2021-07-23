package usersystem.demo.servises;

import usersystem.demo.entities.BornTown;

import java.io.IOException;

public interface BornTownService {
    void seedTowns() throws IOException;
   BornTown townByID(int id);
    int townSize();
}
