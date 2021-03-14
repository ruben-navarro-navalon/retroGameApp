package com.ironhack.edgeservice.auth.security;

import java.io.Serializable;

public class
AuthenticationRequest implements Serializable {

    private String username;
    private String name;
    private String email;
    private String state;
    private String town;
    private String password;


    public AuthenticationRequest() {
    }
    public AuthenticationRequest(String username, String name, String email, String state, String town,String password) {
        setUsername(username);
        setName(name);
        setEmail(email);
        setState(state);
        setTown(town);
        setPassword(password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}