package usersystem.demo.servises.impl;

import org.springframework.stereotype.Service;
import usersystem.demo.entities.BornTown;
import usersystem.demo.entities.CurrentTown;
import usersystem.demo.repositories.CurrentTownRepo;
import usersystem.demo.servises.CurrentTownService;
import usersystem.demo.units.FileUnit;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CurrentTownServiceImpl implements CurrentTownService {
    private final FileUnit fileUnit;
    private final CurrentTownRepo currentTownRepo;


    public CurrentTownServiceImpl(FileUnit fileUnit, CurrentTownRepo currentTownRepo) {
        this.fileUnit = fileUnit;
        this.currentTownRepo = currentTownRepo;

    }

    @Override
    public void seedCurrentTowns() throws IOException {
        if (this.currentTownRepo.count() != 0) {
            return;
        }
        String path = "src/main/resources/users/towns.txt";
        String[] towns = this.fileUnit.readFile(path);
        Arrays.stream(towns).forEach(t -> {
            String[] current = t.split("\\s+");

           CurrentTown currentTown = new CurrentTown(current[0], current[1]);
            this.currentTownRepo.saveAndFlush(currentTown);
        });
    }

    @Override
    public CurrentTown currentTownByID(int id) {
        return currentTownRepo.findById(id).orElse(null);
    }

    @Override
    public int currentTownSize() {
        return (int) currentTownRepo.count();
    }


}