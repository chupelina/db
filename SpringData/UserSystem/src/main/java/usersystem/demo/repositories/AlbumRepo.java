package usersystem.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.demo.entities.Album;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Integer> {

}
