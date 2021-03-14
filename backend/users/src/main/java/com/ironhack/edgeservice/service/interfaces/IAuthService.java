package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.auth.security.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> registerAndCreateToken(AuthenticationRequest authenticationRequest);
    ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}
