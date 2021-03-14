package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.auth.security.AuthenticationRequest;
import com.ironhack.edgeservice.controller.interfaces.IAuthController;
import com.ironhack.edgeservice.dto.UserDTO;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.repository.UserRepository;
import com.ironhack.edgeservice.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController implements IAuthController {

    @Autowired
    IAuthService authService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerAndCreateToken(@RequestBody AuthenticationRequest authenticationRequest){
        return authService.registerAndCreateToken(authenticationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return authService.createAuthenticationToken(authenticationRequest);
    }

    @GetMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findByUsername(@PathVariable String username, Principal principal) {
        System.out.println(principal.getName());
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (user.get().getUsername().equals(principal.getName())) {
                return new UserDTO(user.get());
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden for you!");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

}
