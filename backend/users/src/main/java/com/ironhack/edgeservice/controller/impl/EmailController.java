package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.dto.EmailDTO;
import com.ironhack.edgeservice.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmailController {
    @Autowired
    IEmailService emailService;

    @PostMapping("/email/send")
    public boolean sendEmail(@RequestBody EmailDTO emailDTO) {
        return emailService.sendEmail(emailDTO);
    }
}
