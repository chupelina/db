package com.example.demo.models;

import com.example.demo.entities.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GameAdding {
    @Length(min = 3, max = 100)
    @Pattern(regexp = "[A-Z]+.+", message = "Title should starts with upper case")
    private String title;
    @Positive(message = "The price must be a positive number")
    private BigDecimal price;
    @Positive(message = "The size must be a positive number")
    private double size;
    @Length (min = 11, max = 11, message = "Invalid video id")
    private String trailer;
    private String imageURL;
    @Length(min =20, message = "Too short description")
    private String description;
    private LocalDate releaseDate;

    public GameAdding(String title, BigDecimal price, double size,
                      String trailer, String imageURL,
                      String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageURL = imageURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public GameAdding() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
