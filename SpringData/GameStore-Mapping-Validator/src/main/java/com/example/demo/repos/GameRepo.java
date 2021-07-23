package com.example.demo.repos;

import com.example.demo.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface GameRepo extends JpaRepository<Game, Long> {


    @Modifying
    Game deleteById(long id);

    Game findFirstByTitle(String title);
}

