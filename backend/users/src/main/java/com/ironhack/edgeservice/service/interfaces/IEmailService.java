package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.dto.EmailDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IEmailService {
    public boolean sendEmail(EmailDTO emailDTO);
}
