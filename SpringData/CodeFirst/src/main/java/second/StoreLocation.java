//package second;
//
//import javax.persistence.*;
//import java.util.Objects;
//import java.util.Set;
//@Entity
//@Table(name = "store_locations")
//public class StoreLocation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;
//    @Column(name = "location_name")
//    private String locationName;
//    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class)
//    private Set<Sale> sales;
//
//    public StoreLocation(String locationName, Set<Sale> sales) {
//        this.locationName = locationName;
//        this.sales = sales;
//    }
//
//    public StoreLocation() {
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
//    public String getLocationName() {
//        return locationName;
//    }
//
//    public void setLocationName(String locationName) {
//        this.locationName = locationName;
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
//        StoreLocation that = (StoreLocation) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public String toString() {
//        return "StoreLocation{" +
//                "id=" + id +
//                ", locationName='" + locationName + '\'' +
//                ", sales=" + sales +
//                '}';
//    }
//}
