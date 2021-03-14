package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.auth.security.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    ResponseEntity<?> registerAndCreateToken(AuthenticationRequest authenticationRequest);
    ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}
