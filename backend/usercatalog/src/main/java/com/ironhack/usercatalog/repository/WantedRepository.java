package com.ironhack.usercatalog.repository;

import com.ironhack.usercatalog.model.Wanted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WantedRepository extends JpaRepository<Wanted, Long> {
    @Query(value = "SELECT game_id FROM game_in_wanted INNER JOIN game ON game_in_wanted.game_id=game.id WHERE api_id=:apiId AND wanted_id=:wantedId", nativeQuery = true)
    Optional<Long> findGameIdByApiId(@Param("apiId") Long apiId, @Param("wantedId") Long wantedId);


    // Returns a list of people who wants games that user sells.
    @Query(value = "SELECT username,api_id FROM game_in_wanted INNER JOIN game ON game_in_wanted.game_id=game.id INNER JOIN user ON game_in_wanted.wanted_id=user.id WHERE api_id=:apiId", nativeQuery = true)
    List<String[]> findPeopleWhoWantsWhatISell(@Param("apiId") Long apiId);

}
