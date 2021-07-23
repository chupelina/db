package com.example.demo.runnable;

import com.example.demo.models.GameAdding;
import com.example.demo.models.ShoppingCar;
import com.example.demo.models.UserDto;
import com.example.demo.models.UserLoginDto;
import com.example.demo.services.GameService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class Run implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public Run(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);


        System.out.println("This is my second task if you want to skip press $$");
        String[] current = scan.nextLine().split("\\|");

        while (!current[0].equals("$$")) {
            if (current[0].equals("RegisterUser")) {
                UserDto user = new UserDto(current[1], current[2], current[3], current[4]);
                System.out.println(this.userService.registerUser(user));
            } else if (current[0].equals("LoginUser")) {
                UserLoginDto user = new UserLoginDto(current[1], current[2]);
                System.out.println(this.userService.loginUser(user));
            } else if (current[0].equals("Logout")) {
                System.out.println(userService.logoutUser());
            }
            current = scan.nextLine().split("\\|");
        }

        System.out.println("This is my third task, if you want to skip press $$ ");
        System.out.println("Do not forget to login :)");
        current = scan.nextLine().split("\\|");

        while (!current[0].equals("$$")) {
            if (current[0].equals("AddGame")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate localDate = LocalDate.parse(current[7], formatter);
                GameAdding adding = new GameAdding(current[1],
                        BigDecimal.valueOf(Double.parseDouble(current[2]))
                        , Double.parseDouble(current[3]), current[4], current[5],
                        current[6], localDate);
                System.out.println(this.gameService.addGame(adding));
            } else if (current[0].equals("EditGame")) {
                System.out.println(this.gameService.editGame(current));
            } else if (current[0].equals("DeleteGame")) {
                System.out.println(this.gameService.deleteGame(current[1]));
            }
            current = scan.nextLine().split("\\|");
        }

        System.out.println("This is my forth task, skip-> $$");
        System.out.println("If you want to try owned games type bought|(the name of the game) " +
                "and must be login");
        current = scan.nextLine().split("\\|");


        while (!current[0].equals("$$")) {
            if (current[0].equals("bought")) {
                userService.boughtGames(current[1]);
            } else if (current[0].equals("AllGames")) {
                gameService.printAll();
            } else if (current[0].equals("DetailGame")) {
                gameService.printDetails(current[1]);
            } else if (current[0].equals("OwnedGames")) {
                userService.printAllOwnedGames();
            }
            current = scan.nextLine().split("\\|");
        }

        System.out.println("This is the shopping car, if you want to quit press $$");
        current = scan.nextLine().split("\\|");
        ShoppingCar shoppingCar = new ShoppingCar(gameService, userService);
        while (!current[0].equals("$$")) {
            if (current[0].equals("AddItem")) {
                shoppingCar.addGame(current[1]);
            } else if (current[0].equals("RemoveItem")) {
                shoppingCar.removeGame(current[1]);
            } else if (current[0].equals("BuyItem")) {
                shoppingCar.buyGames();
            }
            current = scan.nextLine().split("\\|");
        }
    }
}

