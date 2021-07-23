package usersystem.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.demo.entities.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findAllByLastTimeLoggedInBefore(LocalDate lastTimeLoggedIn);
    void deleteAllByLastTimeLoggedInAfter(LocalDate lastTime);
    List<User> findAllByEmailContains(String host);
}
