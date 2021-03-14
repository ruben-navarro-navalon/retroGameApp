package com.ironhack.usercatalog.service.impl;

import com.ironhack.usercatalog.dto.CollectionDTO;
import com.ironhack.usercatalog.model.Collection;
import com.ironhack.usercatalog.model.Game;
import com.ironhack.usercatalog.model.Sell;
import com.ironhack.usercatalog.repository.CollectionRepository;
import com.ironhack.usercatalog.repository.GameRepository;
import com.ironhack.usercatalog.repository.SellRepository;
import com.ironhack.usercatalog.service.interfaces.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionService implements ICollectionService {
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    SellRepository sellRepository;
    @Autowired
    GameRepository gameRepository;


    // Returns a collection by id
    public CollectionDTO getCollectionById(Long id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isPresent()) {
            return new CollectionDTO(collection.get().getGameList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection id doesn't exist.");
        }
    }

    // Adds a game to a collection
    public CollectionDTO addGameToCollection(Long id, Long apiId) {
        Game gameToAdd = new Game(apiId);
        gameToAdd = gameRepository.save(gameToAdd);

        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no collection with that id");
        }

        List<Game> games = collection.get().getGameList();
        games.add(gameToAdd);
        collection.get().setGameList(games);

        return new CollectionDTO(collectionRepository.save(collection.get()));
    }

    // Removes a game from a collection
    public CollectionDTO removeGameFromCollection(Long id, Long apiId) {
        Optional<Long> gameIdToRemove = collectionRepository.findGameIdByApiId(apiId, id);
        if (gameIdToRemove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the user collection");
        }

        Optional<Game> gameToRemove = gameRepository.findById(gameIdToRemove.get());
        if (gameToRemove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the database");
        }

        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no collection with that id");
        }

        Optional<Sell> sell = sellRepository.findById(id);
        if (sell.isPresent()) {
            List<Game> gamesInSell = sell.get().getGameList();
            if (gamesInSell.contains(gameToRemove.get())) {
                gamesInSell.remove(gameToRemove.get());
            }
        }

        List<Game> games = collection.get().getGameList();
        games.remove(gameToRemove.get());
        collection.get().setGameList(games);
        collectionRepository.save(collection.get());
        gameRepository.deleteById(gameToRemove.get().getId());

        return new CollectionDTO(collection.get());
    }
}
