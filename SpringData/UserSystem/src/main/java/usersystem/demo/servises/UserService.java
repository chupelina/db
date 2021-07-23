package usersystem.demo.servises;

import usersystem.demo.entities.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;
    int count();
    User userById(int id);
    int findAllBefore(String localDate);
    void deleteAllByLastTimeLoggedInBefore(String lastTime);
    List<User> findAllByEmailContains(String host);
}
