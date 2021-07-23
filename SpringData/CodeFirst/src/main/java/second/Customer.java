//package second;
//
//import javax.persistence.*;
//import java.util.Objects;
//import java.util.Set;
//@Entity
//@Table(name = "customers")
//public class Customer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;
//    private String name;
//    private String email;
//    @Column(name = "credit_card_number")
//    private String creditCardNumber;
//    @OneToMany(mappedBy = "customer", targetEntity = Sale.class)
//    private Set<Sale> sales;
//
//    public Customer() {
//
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
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
//    public String getCreditCardNumber() {
//        return creditCardNumber;
//    }
//
//    public void setCreditCardNumber(String creditCardNumber) {
//        this.creditCardNumber = creditCardNumber;
//    }
//
//    public Set<Sale> getSales() {
//        return sales;
//    }
//
//    public void setSales(Set<Sale> sales) {
//        this.sales = sales;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Customer customer = (Customer) o;
//        return id == customer.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", creditCardNumber='" + creditCardNumber + '\'' +
//                ", sales=" + sales +
//                '}';
//    }
//}
