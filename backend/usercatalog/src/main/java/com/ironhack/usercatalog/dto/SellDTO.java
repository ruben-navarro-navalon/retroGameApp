package com.ironhack.usercatalog.dto;

import com.ironhack.usercatalog.model.Game;
import com.ironhack.usercatalog.model.Sell;

import java.util.List;

public class SellDTO {
    private List<Game> gameList;

    public SellDTO() {
    }

    public SellDTO(List<Game> gameList) {
        setGameList(gameList);
    }

    public SellDTO(Sell sell) {
        setGameList(sell.getGameList());
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
}
