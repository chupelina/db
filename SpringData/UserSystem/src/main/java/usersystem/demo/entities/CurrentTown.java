package usersystem.demo.entities;

import javax.persistence.*;

@Entity
public class CurrentTown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String townName;
    private String country;
    @OneToOne
    private User user;

    public CurrentTown() {
    }

    public CurrentTown(String name, String country) {
        this.townName = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
