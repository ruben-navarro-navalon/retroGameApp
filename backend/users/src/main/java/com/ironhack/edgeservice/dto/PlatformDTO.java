package com.ironhack.edgeservice.dto;

import java.time.LocalDate;

public class PlatformDTO {

    private Integer id;
    private Integer apiId;
    private String name;
    private String shortName;
    private Long gamesCount;
    private String description;
    private String image;
    private LocalDate released;
    private Boolean portable;

    public PlatformDTO() {
    }
    public PlatformDTO(Integer id, Integer apiId, String name, String shortName, Long gamesCount, String description, String image, LocalDate released, Boolean portable) {
        setId(id);
        setApiId(apiId);
        setName(name);
        setShortName(shortName);
        setGamesCount(gamesCount);
        setDescription(description);
        setImage(image);
        setReleased(released);
        setPortable(portable);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(Long gamesCount) {
        this.gamesCount = gamesCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public Boolean getPortable() {
        return portable;
    }

    public void setPortable(Boolean portable) {
        this.portable = portable;
    }
}
