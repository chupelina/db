//package fifth;
//
//import javax.persistence.*;
//@Entity
//@Table(name = "billing_details")
//@Inheritance(strategy = InheritanceType.JOINED)
//public class BillingDetail {
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private int id;
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//    private String number;
//
//    public BillingDetail() {
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
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//}
