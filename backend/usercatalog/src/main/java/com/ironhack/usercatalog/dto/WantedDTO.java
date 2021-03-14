package com.ironhack.usercatalog.dto;

import com.ironhack.usercatalog.model.Game;
import com.ironhack.usercatalog.model.Wanted;

import java.util.List;

public class WantedDTO {
    private List<Game> gameList;

    public WantedDTO() {
    }
    public WantedDTO(Wanted wanted) {
        setGameList(wanted.getGameList());
    }
    public WantedDTO(List<Game> gameList) {
        setGameList(gameList);
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
