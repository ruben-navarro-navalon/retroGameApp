package com.ironhack.usercatalog.dto;

import com.ironhack.usercatalog.model.Collection;
import com.ironhack.usercatalog.model.Game;

import java.util.List;

public class CollectionDTO {
    private List<Game> gameList;

    public CollectionDTO() {
    }
    public CollectionDTO(Collection collection) {
        setGameList(collection.getGameList());
    }
    public CollectionDTO(List<Game> gameList) {
        setGameList(gameList);
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
