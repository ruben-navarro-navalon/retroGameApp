package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.auth.security.AuthenticationRequest;
import com.ironhack.edgeservice.auth.security.AuthenticationResponse;
import com.ironhack.edgeservice.auth.security.JwtUtils;
import com.ironhack.edgeservice.auth.security.MyUserDetailsService;
import com.ironhack.edgeservice.controller.impl.UserCatalogController;
import com.ironhack.edgeservice.dto.UserOfCatalogDTO;
import com.ironhack.edgeservice.model.Role;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.repository.UserRepository;
import com.ironhack.edgeservice.service.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCatalogController userCatalogController;

    // REGISTER:
    public ResponseEntity<?> registerAndCreateToken(AuthenticationRequest authenticationRequest) {
        if (userRepository.existsByUsername(authenticationRequest.getUsername())) {
            return ResponseEntity.badRequest().body("This username is already in use. Please choose another username.");
        }

        User user = new User(authenticationRequest.getUsername(),
                authenticationRequest.getName(),
                authenticationRequest.getEmail(),
                authenticationRequest.getState(),
                authenticationRequest.getTown(),
                authenticationRequest.getPassword());
        user.addRole(new Role("USER", user));

        user = userRepository.save(user);
        userCatalogController.createNewUser(new UserOfCatalogDTO(user.getId(), user.getUsername()));

        return ResponseEntity.ok("User registered successfully!");
    }

    // LOGIN:
    public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }



}
