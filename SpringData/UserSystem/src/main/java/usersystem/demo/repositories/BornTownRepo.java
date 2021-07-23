package usersystem.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.demo.entities.BornTown;

@Repository
public interface BornTownRepo extends JpaRepository<BornTown, Integer> {
}
