package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.User;

public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String state;
    private String town;

    public UserDTO() {
    }
    public UserDTO(Long id, String username, String name, String email, String state, String town) {
        setId(id);
        setUsername(username);
        setName(name);
        setEmail(email);
        setState(state);
        setTown(town);
    }
    public UserDTO(User user){
        setId(user.getId());
        setUsername(user.getUsername());
        setName(user.getName());
        setEmail(user.getEmail());
        setState(user.getState());
        setTown(user.getTown());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
