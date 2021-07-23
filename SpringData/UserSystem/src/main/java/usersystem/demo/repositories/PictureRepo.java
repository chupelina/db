package usersystem.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import usersystem.demo.entities.Picture;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Integer> {
}
