package com.ironhack.edgeservice.dto;

public class GameOfCatalogDTO {
    private Long id;
    private Long apiId;

    public GameOfCatalogDTO() {
    }
    public GameOfCatalogDTO(Long id, Long apiId) {
        setId(id);
        setApiId(apiId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }
}
