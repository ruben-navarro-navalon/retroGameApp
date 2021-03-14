package com.ironhack.emailservice.controller.interfaces;

import com.ironhack.emailservice.model.Email;

public interface IEmailController {
    boolean sendEmail(Email email);
}
