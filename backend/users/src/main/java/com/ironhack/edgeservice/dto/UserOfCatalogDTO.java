package com.ironhack.edgeservice.dto;

public class UserOfCatalogDTO {
    private Long id;
    private String username;

    public UserOfCatalogDTO() {
    }

    public UserOfCatalogDTO(Long id, String username) {
        setId(id);
        setUsername(username);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
