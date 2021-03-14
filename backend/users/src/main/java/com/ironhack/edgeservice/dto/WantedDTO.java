package com.ironhack.edgeservice.dto;

import java.util.List;

public class WantedDTO {
    private List<GameOfCatalogDTO> gameList;

    public WantedDTO() {
    }
    public WantedDTO(List<GameOfCatalogDTO> gameList) {
        setGameList(gameList);
    }

    public List<GameOfCatalogDTO> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameOfCatalogDTO> gameList) {
        this.gameList = gameList;
    }
}
