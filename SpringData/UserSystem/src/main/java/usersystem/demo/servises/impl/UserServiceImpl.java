package usersystem.demo.servises.impl;

import org.springframework.stereotype.Service;
import usersystem.demo.entities.Album;
import usersystem.demo.entities.BornTown;
import usersystem.demo.entities.CurrentTown;
import usersystem.demo.entities.User;
import usersystem.demo.repositories.UserRepo;
import usersystem.demo.servises.AlbumService;
import usersystem.demo.servises.CurrentTownService;
import usersystem.demo.servises.BornTownService;
import usersystem.demo.servises.UserService;
import usersystem.demo.units.FileUnit;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final FileUnit fileUnit;
    private final UserRepo userRepo;
    private final AlbumService albumService;
    private final BornTownService bornTownService;
    private final CurrentTownService currentTownService;

    public UserServiceImpl(FileUnit fileUnit, UserRepo userRepo, AlbumService albumService, BornTownService bornTownService, CurrentTownService currentTownService) {
        this.fileUnit = fileUnit;
        this.userRepo = userRepo;
        this.albumService = albumService;
        this.bornTownService = bornTownService;
        this.currentTownService = currentTownService;
    }

    @Override
    public void seedUsers() throws IOException {
        if(userRepo.count()!=0){
            return;
        }
        String path = "src/main/resources/users/users.txt";
        String[] users = fileUnit.readFile(path);
        for (String u : users) {
            String[] current = u.split("\\s+");
            int age = Integer.parseInt(current[0]);
            String email = current[1];
            if (!isOk(email)) {
                System.out.println("Invalid email");
                break;
            }
            String firstName = current[2];
            Boolean isDelete = current[3].equals("true");
            String LastName = current[4];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            LocalDate logIn = LocalDate.parse(current[5], formatter);
            String password = current[6];
            if (!isLegal(password)) {
                System.out.println("Invalid password");
                break;
            }
            LocalDate registerOn = LocalDate.parse(current[7], formatter);
            String username = current[8];
            BornTown bornTown = getTown();
            CurrentTown currentTown = getCurrentTown();
            Set<Album> albumSet = albumFinder();
            User user = new User(age, email, firstName, isDelete, LastName,
                    logIn, password, registerOn, username, albumSet, bornTown, currentTown);
            userRepo.saveAndFlush(user);

        }

    }

    private boolean isLegal(String password) {
        if (30 <= password.length() || password.length() <= 4) {
            return false;
        }
        boolean isDigit = false;
        boolean isLowercase = false;
        boolean isUpperCase = false;
        boolean containsSymbol = false;
        String specialSymbols = "!@#$%^&*()_+<>?";
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                isDigit = true;
            }
            if (Character.isLowerCase(c)) {
                isLowercase = true;
            }
            if (Character.isUpperCase(c)) {
                isUpperCase = true;
            }
            if (specialSymbols.contains(c + "")) {
                containsSymbol = true;
            }

        }
        if (isDigit && isLowercase && isUpperCase && containsSymbol) {
            return true;
        }
        return false;

    }

    private boolean isOk(String email) {
        if (email.startsWith("-") || email.startsWith(".") || email.startsWith("_")) {
            return false;
        }
        Pattern pattern = Pattern.compile("([A-Za-z0-9]+[\\.\\-_]?[A-Za-z0-9]+)@[A-Za-z]+(\\-?\\.?[A-Za-z]*)+(\\.[A-Za-z]+)");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    private CurrentTown getCurrentTown() {
        Random random = new Random();
        int index = random.nextInt(currentTownService.currentTownSize()) + 1;
        return currentTownService.currentTownByID(index);
    }

    private BornTown getTown() {
        Random random = new Random();
        int index = random.nextInt(bornTownService.townSize()) + 1;
        return bornTownService.townByID(index);
    }

    @Override
    public int count() {
        return (int) userRepo.count();
    }

    @Override
    public User userById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    private Set<Album> albumFinder() {
        Set<Album> albums = new LinkedHashSet<>();
        Random rd = new Random();
        int index = rd.nextInt(5) + 1;
        for (int i = 0; i < index; i++) {
            int index1 = rd.nextInt(albumService.size()) + 1;
            albums.add(albumService.getById(index1));
        }
        return albums;
    }

    public int findAllBefore(String localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse(localDate, formatter);
        List<User> users= userRepo.findAllByLastTimeLoggedInBefore(releaseDate);
        for (User user : users) {
            user.setDeleted(true);
            userRepo.flush();
        }

        return users.size();
    }

    @Override
    public  void deleteAllByLastTimeLoggedInBefore(String lastTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse(lastTime, formatter);
         userRepo.deleteAllByLastTimeLoggedInAfter(releaseDate);
    }

    @Override
    public List<User> findAllByEmailContains(String host) {
        return userRepo.findAllByEmailContains(host);
    }


}
