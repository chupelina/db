package usersystem.demo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", length = 30)
    private String userName;
    private String password;
    private String email;
    private LocalDate registeredOn;
    private LocalDate lastTimeLoggedIn;
    private int age;
    private boolean isDeleted;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "user")

    private Set<Album> albums;
    @OneToOne(cascade = CascadeType.PERSIST)
    private BornTown bornTown;
    @OneToOne(cascade = CascadeType.PERSIST)
    private CurrentTown currentTown;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<User> users;
    @ManyToMany(cascade = CascadeType.PERSIST,mappedBy = "users")
    private Set<User> friends;

    public User() {

    }

    public String fullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public User(int age, String email, String firstName,
                Boolean isDelete, String lastName, LocalDate logIn,
                String password, LocalDate registerOn, String username
            , Set<Album> albumSet, BornTown bornTown, CurrentTown currentTown) {
        this.age = age;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isDeleted = isDelete;
        this.lastTimeLoggedIn = logIn;
        this.password = password;
        this.registeredOn = registerOn;
        this.userName = username;
        this.albums = albumSet;
        this.bornTown = bornTown;
        this.currentTown = currentTown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
