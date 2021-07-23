package usersystem.demo.servises.impl;

import org.springframework.stereotype.Service;
import usersystem.demo.entities.BornTown;
import usersystem.demo.repositories.BornTownRepo;
import usersystem.demo.servises.BornTownService;
import usersystem.demo.units.FileUnit;

import java.io.IOException;
import java.util.Arrays;

@Service
public class BornTownServiceImpl implements BornTownService {
    private final FileUnit fileUnit;
    private final BornTownRepo townRepo;



    public BornTownServiceImpl(FileUnit fileUnit, BornTownRepo townRepo) {
        this.fileUnit = fileUnit;
        this.townRepo = townRepo;

    }

    @Override
    public void seedTowns() throws IOException {
        if (this.townRepo.count() != 0) {
            return;
        }
        String path = "src/main/resources/users/towns.txt";
        String[] towns = this.fileUnit.readFile(path);
        Arrays.stream(towns).forEach(t -> {
            String[] current = t.split("\\s+");

            BornTown bornTown = new BornTown(current[0],current[1] );
             this.townRepo.saveAndFlush(bornTown);
        });

    }


    @Override
    public BornTown townByID(int id) {
      return townRepo.findById(id).orElse(townRepo.getOne(1));
    }

    @Override
    public int townSize() {
        return (int) this.townRepo.count();
    }
}
