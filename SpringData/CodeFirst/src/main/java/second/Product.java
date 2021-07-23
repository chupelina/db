//package second;;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.Objects;
//import java.util.Set;
//@Entity
//@Table(name = "products")
//
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name="name", nullable = false)
//    private String name;
//    @Column(name= "quantity")
//    private double quantity;
//    @Column(name= "price")
//    private BigDecimal price;
//    @OneToMany(mappedBy = "product", targetEntity = Sale.class)
//    private Set<Sale> sales;
//
//    public Product() {
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
//    public double getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(double quantity) {
//        this.quantity = quantity;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
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
//        Product product = (Product) o;
//        return id == product.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", quantity=" + quantity +
//                ", price=" + price +
//                ", sales=" + sales +
//                '}';
//    }
//}
