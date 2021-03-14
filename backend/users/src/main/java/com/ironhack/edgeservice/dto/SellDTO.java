package com.ironhack.edgeservice.dto;

import java.util.List;

public class SellDTO {
    private List<GameOfCatalogDTO> gameList;

    public SellDTO() {
    }

    public SellDTO(List<GameOfCatalogDTO> gameList) {
        setGameList(gameList);
    }

    public List<GameOfCatalogDTO> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameOfCatalogDTO> gameList) {
        this.gameList = gameList;
    }
}
