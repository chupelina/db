package gson.demo.repos;

import gson.demo.entities.Product;
import gson.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
@Query("select u from User u where u.sold.size>=1 order by u.lastName , u.firstName")
    List<User> findAllBySold();
}
