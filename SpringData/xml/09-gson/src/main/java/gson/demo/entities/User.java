package gson.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    private String firstName;
    @NonNull
    private String lastName;
    @ManyToMany
    @Column(name = "users_friends")
    private Set<User> friend;
    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private Set<Product> sold;
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Product> bought;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public Set<User> getFriend() {
        return friend;
    }

    public void setFriend(Set<User> friend) {
        this.friend = friend;
    }

    public Set<Product> getSold() {
        return sold;
    }

    public void setSold(Set<Product> sold) {
        this.sold = sold;
    }

    public Set<Product> getBought() {
        return bought;
    }

    public void setBought(Set<Product> bought) {
        this.bought = bought;
    }
}
