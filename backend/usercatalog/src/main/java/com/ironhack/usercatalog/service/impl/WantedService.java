package com.ironhack.usercatalog.service.impl;

import com.ironhack.usercatalog.dto.CollectionDTO;
import com.ironhack.usercatalog.dto.WantedDTO;
import com.ironhack.usercatalog.model.Collection;
import com.ironhack.usercatalog.model.Game;
import com.ironhack.usercatalog.model.Wanted;
import com.ironhack.usercatalog.repository.CollectionRepository;
import com.ironhack.usercatalog.repository.GameRepository;
import com.ironhack.usercatalog.repository.WantedRepository;
import com.ironhack.usercatalog.service.interfaces.IWantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WantedService implements IWantedService {
    @Autowired
    WantedRepository wantedRepository;
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    GameRepository gameRepository;

    // Retuns the wanted list of a user.
    public WantedDTO getWantedById(Long id) {
        Optional<Wanted> wanted = wantedRepository.findById(id);
        if (wanted.isPresent()) {
            return new WantedDTO(wanted.get().getGameList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wanted id doesn't exist.");
        }
    }

    // Adds a game to the wanted list of a user.
    public WantedDTO addGameToWanted(Long id, Long apiId) {
        Game gameToAdd = new Game(apiId);
        gameToAdd = gameRepository.save(gameToAdd);

        Optional<Wanted> wanted = wantedRepository.findById(id);
        if (wanted.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no wanted with that id");
        }

        List<Game> games = wanted.get().getGameList();
        games.add(gameToAdd);
        wanted.get().setGameList(games);

        return new WantedDTO(wantedRepository.save(wanted.get()));
    }

    // Removes a game from the wanted list of a user.
    public WantedDTO removeGameFromWanted(Long id, Long apiId) {
        Optional<Long> gameIdToRemove = wantedRepository.findGameIdByApiId(apiId, id);
        if (gameIdToRemove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the user wanted list");
        }

        Optional<Game> gameToRemove = gameRepository.findById(gameIdToRemove.get());
        if (gameToRemove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the database");
        }

        Optional<Wanted> wanted = wantedRepository.findById(id);
        if (wanted.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no wanted list with that id");
        }

        List<Game> games = wanted.get().getGameList();
        games.remove(gameToRemove.get());
        wanted.get().setGameList(games);
        wantedRepository.save(wanted.get());
        gameRepository.deleteById(gameToRemove.get().getId());

        return new WantedDTO(wanted.get());
    }

    // Moves a game from the wanted list of a user to the collection.
    public CollectionDTO moveGameFromWantedToCollection(Long id, Long apiId) {
        Optional<Long> gameIdToMove = wantedRepository.findGameIdByApiId(apiId, id);
        if (gameIdToMove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the user wanted list");
        }

        Optional<Game> gameToMove = gameRepository.findById(gameIdToMove.get());
        if (gameToMove.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This game is not present in the database");
        }

        Optional<Wanted> wanted = wantedRepository.findById(id);
        if (wanted.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no wanted list with that id");
        }

        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no collection list with that id");
        }

        List<Game> gamesInWanted = wanted.get().getGameList();
        gamesInWanted.remove(gameToMove.get());
        wanted.get().setGameList(gamesInWanted);
        wantedRepository.save(wanted.get());

        List<Game> gamesInCollection = collection.get().getGameList();
        gamesInCollection.add(gameToMove.get());
        collection.get().setGameList(gamesInCollection);
        collectionRepository.save(collection.get());

        return new CollectionDTO(collection.get());
    }

    // Returns a list of people who wants what user sell.
    public List<String[]> getPeopleWhoWantsWhatISell(Long apiId) {
        return wantedRepository.findPeopleWhoWantsWhatISell(apiId);
    }
}