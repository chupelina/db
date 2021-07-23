//package second;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.Objects;
//
//@Entity
//@Table(name = "sales")
//public class Sale {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;
//
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id", referencedColumnName = "id")
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name="store_location_id", referencedColumnName = "id")
//    private StoreLocation storeLocation;
//    private LocalDateTime date;
//
//
//    public Sale() {
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
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public StoreLocation getStoreLocation() {
//        return storeLocation;
//    }
//
//    public void setStoreLocation(StoreLocation storeLocation) {
//        this.storeLocation = storeLocation;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Sale sale = (Sale) o;
//        return id == sale.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public String toString() {
//        return "Sale{" +
//                "id=" + id +
//                ", product=" + product +
//                ", customer=" + customer +
//                ", storeLocation=" + storeLocation +
//                ", date=" + date +
//                '}';
//    }
//}
