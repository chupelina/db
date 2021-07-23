package com.example.demo.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String fullName;
    @NonNull
    private boolean isAdmin;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games= new HashSet<>();

    public User() {
    }

    public User(@NonNull String email, @NonNull String password, @NonNull String fullName, boolean isAdmin, Set<Game> games) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
        this.games =games;
    }

    public Long getId() {
        return id;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getFullName() {
        return fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setFullName(@NonNull String fullName) {
        this.fullName = fullName;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setGames(Game game) {
        this.games.add(game);
    }
}
