package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.EmailDTO;
import com.ironhack.edgeservice.dto.PlatformDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("email-service")
public interface EmailClient {

    @PostMapping("/email/send")
    public boolean sendEmail(@RequestBody EmailDTO emailDTO);

}
