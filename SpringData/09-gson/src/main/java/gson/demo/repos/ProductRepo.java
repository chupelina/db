package gson.demo.repos;

import gson.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
//@Query("select p from Product p  where p.price between 500 and 1000 " +
//        "order by p.price ")
List<Product> findAllByPriceBetweenOrderByPriceDesc(BigDecimal min , BigDecimal max);
}
