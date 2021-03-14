package com.ironhack.edgeservice.dto;

import java.util.List;

public class CollectionDTO {
    private List<GameOfCatalogDTO> gameList;

    public CollectionDTO() {
    }
    public CollectionDTO(List<GameOfCatalogDTO> gameList) {
        setGameList(gameList);
    }

    public List<GameOfCatalogDTO> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameOfCatalogDTO> gameList) {
        this.gameList = gameList;
    }
}
