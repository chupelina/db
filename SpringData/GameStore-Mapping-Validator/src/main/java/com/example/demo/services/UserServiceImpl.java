package com.example.demo.services;

import com.example.demo.entities.Game;
import com.example.demo.entities.User;
import com.example.demo.models.UserDt;
import com.example.demo.models.UserDto;
import com.example.demo.models.UserLoginDto;
import com.example.demo.repos.UserRepo;
import com.example.demo.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private UserDt loggedUSer;
    private final GameService gameService;

    @Autowired
    public UserServiceImpl(ValidatorUtil validatorUtil,
                           ModelMapper modelMapper,
                           UserRepo userRepo, GameService gameService) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.gameService = gameService;
    }

    @Override
    public String registerUser(UserDto userDto) {
        StringBuilder sb = new StringBuilder();
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            sb.append("Not matching passwords.");
        } else if (this.validatorUtil.isValid(userDto)) {
            User user = this.modelMapper.map(userDto, User.class);
            if (userRepo.count() == 0) {
                user.setAdmin(true);
            } else {
                user.setAdmin(false);
            }
            sb.append(String.format("%s was registered", user.getFullName()));
            this.userRepo.saveAndFlush(user);
        } else {
            this.validatorUtil.violations(userDto).stream().forEach(e ->
                    sb.append(String.format("%s%n", e.getMessage())));
        }

        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto user) {
        StringBuilder sb = new StringBuilder();
        Optional<User> current = userRepo.findFirstByEmailAndPassword(user.getEmail(),
                user.getPassword());
        if (current.isPresent()) {
            if (loggedUSer != null) {
                sb.append("User is logged in.");
            } else {
                this.loggedUSer = this.modelMapper.map(current.get(), UserDt.class);
                this.gameService.setLoggedUser(loggedUSer);
                sb.append("Successfully logged in ").append(loggedUSer.getFullName());
            }
        } else {
            sb.append("Incorrect email/password");
        }
        return sb.toString();
    }

    @Override
    public String logoutUser() {
        StringBuilder sb = new StringBuilder();
        if (loggedUSer.getEmail() == null) {
            sb.append("Cannot log out. No user was logged in.");
        } else {
            sb.append(String.format("User %s successfully logged out",
                    loggedUSer.getFullName()));
            loggedUSer.setEmail(null);
            loggedUSer.setFullName(null);
        }
        return sb.toString();
    }

    @Override
    public void boughtGames(String title) {
        Game game = gameService.findByTitle(title);
        User user = userRepo.findFirstByEmail(loggedUSer.getEmail());
        user.setGames(game);
        userRepo.saveAndFlush(user);
    }

    @Override
    public void printAllOwnedGames() {
        User user = userRepo.findFirstByEmail(loggedUSer.getEmail());
        user.getGames().forEach(e->
                System.out.println(e.getTitle()));
    }

    @Override
    public User findLoggedOne() {
        return userRepo.findFirstByEmail(loggedUSer.getEmail());
    }
}
