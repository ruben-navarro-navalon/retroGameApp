package com.ironhack.usercatalog.service.impl;

import com.ironhack.usercatalog.dto.CollectionDTO;
import com.ironhack.usercatalog.dto.SellDTO;
import com.ironhack.usercatalog.model.Collection;
import com.ironhack.usercatalog.model.Game;
import com.ironhack.usercatalog.model.Sell;
import com.ironhack.usercatalog.model.Wanted;
import com.ironhack.usercatalog.repository.CollectionRepository;
import com.ironhack.usercatalog.repository.GameRepository;
import com.ironhack.usercatalog.repository.SellRepository;
import com.ironhack.usercatalog.service.interfaces.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SellService implements ISellService {
    @Autowired
    SellRepository sellRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    CollectionRepository collectionRepository;

    // Returns the sell list from a user.
    public SellDTO getSellById(Long id) {
        Optional<Sell> sell = sellRepository.findById(id);
        if (sell.isPresent()) {
            return new SellDTO(sell.get().getGameList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sell id doesn't exist.");
        }
    }

    // Adds a game to the sell list of a user.
    public SellDTO addGameToSell(Long id, Long apiId) {
        Optional<Long> gameIdToSell = collectionRepository.findGameIdByApiId(apiId, id);
        if (gameIdToSell.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't have this game");
        }
        Game gameToSell= gameRepository.findById(gameIdToSell.get()).get();

        Optional<Sell> sell = sellRepository.findById(id);
        if (sell.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no sell with that id");
        }

        List<Game> games = sell.get().getGameList();
        games.add(gameToSell);
        sell.get().setGameList(games);

        return new SellDTO(sellRepository.save(sell.get()));
    }

    // Removes a game from the sell list of a user.
    public SellDTO removeGameFromSell(Long id, Long apiId) {
        Optional<Long> gameIdToRemove = sellRepository.findGameIdByApiId(apiId, id);
        if (gameIdToRemove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the user sell list");
        }

        Optional<Game> gameToRemove = gameRepository.findById(gameIdToRemove.get());
        if (gameToRemove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the database");
        }

        Optional<Sell> sell = sellRepository.findById(id);
        if (sell.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no sell list with that id");
        }

        List<Game> gamesInSell = sell.get().getGameList();
        gamesInSell.remove(gameToRemove.get());
        sell.get().setGameList(gamesInSell);
        sellRepository.save(sell.get());

        return new SellDTO(sell.get());
    }

    // Returns a list of people who sells games what user wants.
    public List<String[]> getPeopleWhoSellsWhatIWant(Long apiId) {
        return sellRepository.findPeopleWhoSellsWhatIWant(apiId);
    }
}