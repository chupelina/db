//package fifth;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name= "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name = "first_name")
//    private int firstName;
//    @Column(name= "last_name")
//    private int lastName;
//    private String email;
//    private String password;
//    @OneToMany(mappedBy = "user", targetEntity = BillingDetail.class)
//    private Set<BillingDetail> details;
//
//    public User() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(int firstName) {
//        this.firstName = firstName;
//    }
//
//    public int getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(int lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<BillingDetail> getDetails() {
//        return details;
//    }
//
//    public void setDetails(Set<BillingDetail> details) {
//        this.details = details;
//    }
//}
