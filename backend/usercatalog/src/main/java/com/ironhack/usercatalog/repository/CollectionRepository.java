package com.ironhack.usercatalog.repository;

import com.ironhack.usercatalog.model.Collection;
import com.ironhack.usercatalog.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

    // Uses the apiId of a game inside a collection to return that game DB id.
    @Query(value = "SELECT game_id FROM game_in_collection INNER JOIN game ON game_in_collection.game_id=game.id WHERE api_id=:apiId AND collection_id=:collectionId", nativeQuery = true)
    Optional<Long> findGameIdByApiId(@Param("apiId") Long apiId, @Param("collectionId") Long collectionId);
}
