package com.softuni.springintroex.repozitory;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Transactional
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
     List<Book> findByAgeRestrictionOrderById(AgeRestriction ageRestriction);

     Set<Book> findAllByEditionTypeAndCopiesLessThan(
             EditionType editionType,int copies);

     Set<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal minPrice, BigDecimal maxPrice);

     @Query("select b from Book b where substring(b.releaseDate,1,4) not like :year")
     Set<Book> findAllByReleaseDateNotIn(String year);

     Set<Book> findAllByReleaseDateBefore(LocalDate localDate);

     Set<Book> findAllByTitleContaining(String text);

     Set<Book> findAllByAuthor(Author author);

     @Query("Select b from Book b where length(b.title)> :number ")
     Set<Book> findAllByTitle(@Param("number") int number);

     Book findBookByTitleEquals(String title);

     @Query("update  Book b set b.copies = b.copies+ :bookCopies " +
             "where b.releaseDate> :date ")
     @Modifying
     int increaseBooksCopies(@Param("bookCopies") int copies,
                                   @Param("date") LocalDate date);
     @Modifying
     int deleteAllByCopiesLessThan(int copies);
}
