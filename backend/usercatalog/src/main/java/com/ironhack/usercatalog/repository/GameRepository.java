package com.ironhack.usercatalog.repository;

import com.ironhack.usercatalog.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByApiId(Long apiId);
}
