package com.ironhack.emailservice.controller.impl;

import com.ironhack.emailservice.controller.interfaces.IEmailController;
import com.ironhack.emailservice.model.Email;
import com.ironhack.emailservice.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmailController implements IEmailController {

    @Autowired
    IEmailService emailService;

    @PostMapping("/email/send")
    @ResponseStatus(HttpStatus.OK)
    public boolean sendEmail(@RequestBody Email email) {
        return emailService.sendEmail(email);
    }
}
