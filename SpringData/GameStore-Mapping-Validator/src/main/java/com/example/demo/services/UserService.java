package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.models.UserDto;
import com.example.demo.models.UserLoginDto;

public interface UserService {

    String registerUser(UserDto userDto);

    String loginUser(UserLoginDto user);

    String logoutUser();

    void boughtGames(String title);

    void printAllOwnedGames();

    User findLoggedOne();
}
