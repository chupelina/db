package usersystem.demo.cintrillers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import usersystem.demo.entities.User;
import usersystem.demo.servises.*;

import java.util.List;
import java.util.Scanner;

@Controller
public class AppController implements CommandLineRunner {
    private final BornTownService bornTownService;
    private final PictureService pictureService;
    private final AlbumService albumService;
    private final UserService userService;
    private final CurrentTownService currentTownService;

    public AppController(BornTownService bornTownService, PictureService pictureService, AlbumService albumService, UserService userService, CurrentTownService currentTownService) {
        this.bornTownService = bornTownService;
        this.pictureService = pictureService;
        this.albumService = albumService;
        this.userService = userService;
        this.currentTownService = currentTownService;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scan = new Scanner(System.in);
        this.pictureService.seedPictures();
        this.albumService.seedAlbums();
        this.bornTownService.seedTowns();
        this.currentTownService.seedCurrentTowns();
        this.userService.seedUsers();


        System.out.println("Please give me the date of the last logging in following format :d/M/yyyy");
        String date = scan.nextLine();
        int n = this.userService.findAllBefore(date);
        System.out.println(n);
        //I'm not working! My writer goes mad at me
       // this.userService.deleteAllByLastTimeLoggedInAfter(date);
        System.out.println("Please give me valid host like: @abv.bg ");
        String host = scan.nextLine();
        List<User> userList = userService.findAllByEmailContains(host);
        for (User user : userList) {
            System.out.println(user.getUserName()+" "+ user.getEmail());
        }
    }
}
