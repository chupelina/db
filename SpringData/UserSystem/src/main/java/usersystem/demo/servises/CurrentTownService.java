package usersystem.demo.servises;

import usersystem.demo.entities.BornTown;
import usersystem.demo.entities.CurrentTown;

import java.io.IOException;

public interface CurrentTownService {
    void seedCurrentTowns() throws IOException;
   CurrentTown currentTownByID(int id);
    int currentTownSize();
}
