package com.example.demo.entities;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name= "games")
public class Game {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NonNull
    private String title;
    private String trailer;
    private String imageURL;
    private double size;
    @NonNull
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
@ManyToMany(mappedBy = "games", fetch = FetchType.EAGER)
    private Set<User> users;

    public Game(@NonNull String title, String trailer, String imageURL, double size, @NonNull BigDecimal price,
                String description, LocalDate releaseDate, Set<User> users) {
        this.title = title;
        this.trailer = trailer;
        this.imageURL = imageURL;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
        this.users = users;
    }

    public Game() {
    }

    public Long getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public double getSize() {
        return size;
    }

    @NonNull
    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setPrice(@NonNull BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
