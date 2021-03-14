package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.client.EmailClient;
import com.ironhack.edgeservice.dto.EmailDTO;
import com.ironhack.edgeservice.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmailService implements IEmailService {
    @Autowired
    EmailClient emailClient;

    public boolean sendEmail(@RequestBody EmailDTO emailDTO) {
        return emailClient.sendEmail(emailDTO);
    }
}
