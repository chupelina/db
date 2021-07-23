package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.library.models.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer> {
}
