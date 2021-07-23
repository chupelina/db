package com.example.springlab.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "description", length = 1000)
    private String description;
    @Enumerated(EnumType.ORDINAL )
    private EditionType editionType;
    @Column(name = "price",nullable = false)
    private BigDecimal price;
    @Column(name = "copies",nullable = false)
    private int copies;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Enumerated(EnumType.ORDINAL )
    private AgeRestriction ageRestriction;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Category> categories;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Author author;

    public Book(String title, EditionType editionType,
                BigDecimal price, int copies, LocalDate releaseDate, AgeRestriction ageRestriction,
                Author author,Set<Category> categories) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
        this.author=author;
        this.categories = categories;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
