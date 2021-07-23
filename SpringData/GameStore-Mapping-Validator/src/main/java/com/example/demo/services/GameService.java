package com.example.demo.services;

import com.example.demo.entities.Game;
import com.example.demo.models.GameAdding;
import com.example.demo.models.UserDt;

public interface GameService {
    String addGame(GameAdding adding);

    void setLoggedUser(UserDt userDt);

   String editGame(String[] current);

    String deleteGame(String s);

    void printAll();

    void printDetails(String game);

    Game findByTitle(String title);
}
