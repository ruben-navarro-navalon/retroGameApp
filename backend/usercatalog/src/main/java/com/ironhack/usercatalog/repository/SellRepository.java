package com.ironhack.usercatalog.repository;

import com.ironhack.usercatalog.model.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

    // Uses the apiId of a game inside a sell list to return that game DB id.
    @Query(value = "SELECT game_id FROM game_in_sell INNER JOIN game ON game_in_sell.game_id=game.id WHERE api_id=:apiId AND sell_id=:sellId", nativeQuery = true)
    Optional<Long> findGameIdByApiId(@Param("apiId") Long apiId, @Param("sellId") Long sellId);



    // Returns a list of people who sells games that user wants.
    @Query(value = "SELECT username,api_id FROM game_in_sell INNER JOIN game ON game_in_sell.game_id=game.id INNER JOIN user ON game_in_sell.sell_id=user.id WHERE api_id=:apiId", nativeQuery = true)
    List<String[]> findPeopleWhoSellsWhatIWant(@Param("apiId") Long apiId);
}
