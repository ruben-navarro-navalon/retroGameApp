package com.ironhack.emailservice.service.interfaces;

import com.ironhack.emailservice.model.Email;

public interface IEmailService {
    boolean sendEmail(Email email);
    boolean sendEmailTool(String textMessage, String address, String subject);
}
