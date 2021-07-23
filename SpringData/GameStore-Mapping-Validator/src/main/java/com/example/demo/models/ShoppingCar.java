package com.example.demo.models;

import com.example.demo.entities.Game;
import com.example.demo.entities.User;
import com.example.demo.services.GameService;
import com.example.demo.services.GameServiceImpl;
import com.example.demo.services.UserService;
import com.example.demo.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShoppingCar {
    private List<Game> games;
    private final GameService gameService;
    private final UserService userService;

    public ShoppingCar(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
        this.games = new ArrayList<>();
    }

    public void addGame(String game) {
        Game found = gameService.findByTitle(game);
        if (found == null) {
            System.out.println("Game not found");
        } else {
            games.add(found);
            System.out.println(found.getTitle() + " added to cart.");
        }
    }

    public void removeGame(String game) {
        Game found = gameService.findByTitle(game);
        if (!games.contains(found)) {
            System.out.println("Game not found");
        } else if (games.isEmpty()) {
            System.out.println("There is nothing in the ShoppingCar");
        } else {
            games.remove(found);
            System.out.println(found.getTitle() + " removed from cart.");
        }
    }

    public void buyGames() {
        User user = userService.findLoggedOne();
        Set<Game> currentGames = user.getGames();
        System.out.println("Successfully bought games:");
        for (Game game : games) {
            currentGames.add(game);
            System.out.println("-" + game.getTitle());
        }

    }
}
