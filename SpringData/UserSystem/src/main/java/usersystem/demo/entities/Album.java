package usersystem.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name;
    private String backgroundColor;
    private boolean isPublic;

    @ManyToMany
    private Set<Picture>  pictures;
    @ManyToOne
    private User user;

    public Album(String color, Boolean isVisible,
                 String name, Set<Picture> pictures) {
        this.backgroundColor=color;
        this.isPublic=isVisible;
        this.name=name;
        this.pictures = pictures;
    }

    public Album() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
