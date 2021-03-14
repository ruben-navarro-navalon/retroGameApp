package com.ironhack.edgeservice.dto;

public class EmailDTO {
    private String textMessage;
    private String address;
    private String subject;

    public EmailDTO() {
    }
    public EmailDTO(String textMessage, String address, String subject) {
        setTextMessage(textMessage);
        setAddress(address);
        setSubject(subject);
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
