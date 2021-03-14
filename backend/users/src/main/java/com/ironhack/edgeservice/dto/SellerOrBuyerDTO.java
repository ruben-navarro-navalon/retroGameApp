package com.ironhack.edgeservice.dto;

public class SellerOrBuyerDTO {
    private String username;
    private String email;
    private String state;
    private String town;
    private String apiId;
    private String whatWants;

    public SellerOrBuyerDTO() {
    }
    public SellerOrBuyerDTO(String[] fromClient, String[] stateTownAndEmail, String whatWants) {
        setUsername(fromClient[0]);
        setEmail(stateTownAndEmail[2]);
        setState(stateTownAndEmail[0]);
        setTown(stateTownAndEmail[1]);
        setApiId(fromClient[1]);
        setWhatWants(whatWants);
    }
    public SellerOrBuyerDTO(String username, String email, String state, String town, String apiId, String whatWants) {
        setUsername(username);
        setEmail(email);
        setState(state);
        setTown(town);
        setApiId(apiId);
        setWhatWants(whatWants);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getWhatWants() {
        return whatWants;
    }

    public void setWhatWants(String whatWants) {
        this.whatWants = whatWants;
    }
}
