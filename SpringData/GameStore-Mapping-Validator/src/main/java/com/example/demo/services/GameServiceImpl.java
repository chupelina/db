package com.example.demo.services;

import com.example.demo.entities.Game;
import com.example.demo.models.GameAdding;
import com.example.demo.models.UserDt;
import com.example.demo.models.UserLoginDto;
import com.example.demo.repos.GameRepo;
import com.example.demo.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final GameRepo gameRepo;
    private UserDt userDt;

    @Autowired
    public GameServiceImpl(ValidatorUtil validatorUtil,
                           ModelMapper modelMapper, GameRepo gameRepo) {
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gameRepo = gameRepo;
    }

    @Override
    public String addGame(GameAdding adding) {
        StringBuilder sb = new StringBuilder();
        if (userDt == null || !userDt.isAdmin()) {
            return "You do not have the power to do this :)";
        }
        if (validatorUtil.isValid(adding)) {
            if (adding.getImageURL().startsWith("http://") || adding.getImageURL().startsWith("https://")) {
                Game game = modelMapper.map(adding, Game.class);
                gameRepo.saveAndFlush(game);
                sb.append("Added " + game.getTitle());
            } else {
                sb.append("Invalid image URL");
            }
        } else {
            validatorUtil.violations(adding).forEach(e ->
                    sb.append(e.getMessage()).append(System.lineSeparator()));
        }
        return sb.toString().trim();
    }

    public void setLoggedUser(UserDt userDt) {
        this.userDt = userDt;
    }

    @Override
    public String editGame(String[] current) {
        if (userDt == null || !userDt.isAdmin()) {
            return "You do not have the power to do this :)";
        }
        int id = Integer.parseInt(current[1]);
        Optional<Game> game = gameRepo.findById((long) id);
        if (!game.isPresent()) {
            return "There is no such game in the data base";
        }
        for (int i = 2; i < current.length; i++) {
            String[] tokens = current[i].split("=");
            switch (tokens[0]) {
                case "title":
                    game.get().setTitle(tokens[1]);
                    break;
                case "price":
                    game.get().setPrice(new BigDecimal(tokens[1]));
                    break;
                case "size":
                    game.get().setSize(Double.parseDouble(tokens[1]));
                    break;
                case "trailer":
                    game.get().setTrailer(tokens[1]);
                    break;
                case "imageURL":
                    game.get().setImageURL(tokens[1]);
                    break;
                case "description":
                    game.get().setDescription(tokens[1]);
                    break;
                case "releaseDate":
                    LocalDate date = LocalDate.parse(tokens[1]);
                    game.get().setReleaseDate(date);
                    break;
            }
        }
        gameRepo.saveAndFlush(game.get());
        return "Edited " + game.get().getTitle();
    }

    @Override
    public String deleteGame(String s) {
        if (userDt == null || !userDt.isAdmin()) {
            return "You do not have the power to do this :)";
        }
        String output = "";
        int id = Integer.parseInt(s);
        Optional<Game> game = gameRepo.findById((long) id);
        if (game.isPresent()) {
            output = "Deleted " + game.get().getTitle();
            gameRepo.deleteById((long) id);

        } else {
            return "This game does not exist in my data base";
        }
        return output;
    }

    @Override
    public void printAll() {
        gameRepo.findAll().forEach(e ->
                System.out.println(e.getTitle() + " " + e.getPrice()));
    }

    @Override
    public void printDetails(String game) {
        Game current = gameRepo.findFirstByTitle(game);
        if (current == null) {
            System.out.println("This game is not in data base");
        } else {
            System.out.println("Title: " + current.getTitle());
            System.out.println("Price: " + current.getPrice());
            System.out.println("Description: " + current.getDescription());
            System.out.println("Release date: " + current.getReleaseDate());
        }
    }

    @Override
    public Game findByTitle(String title) {
        Game current = gameRepo.findFirstByTitle(title);
        if (current == null) {
            System.out.println("This game is not in data base");
        }
        return current;
    }
}
