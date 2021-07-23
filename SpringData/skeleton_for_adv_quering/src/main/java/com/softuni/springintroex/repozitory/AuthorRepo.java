package com.softuni.springintroex.repozitory;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedStoredProcedureQuery;
import java.util.List;
import java.util.Set;


@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Set<Author> findAllByFirstNameEndingWith(String text);

    Set<Author> findAllByLastNameStartingWith(String text);


    Author  findFirstByFirstNameAndLastName(String first, String last);
}
