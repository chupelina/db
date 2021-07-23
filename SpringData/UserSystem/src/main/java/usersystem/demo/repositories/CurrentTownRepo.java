package usersystem.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.demo.entities.CurrentTown;

@Repository
public interface CurrentTownRepo extends JpaRepository<CurrentTown, Integer > {
}
