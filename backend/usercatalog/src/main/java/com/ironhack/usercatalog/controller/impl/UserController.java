package com.ironhack.usercatalog.controller.impl;

import com.ironhack.usercatalog.controller.interfaces.IUserController;
import com.ironhack.usercatalog.model.User;
import com.ironhack.usercatalog.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController implements IUserController {
    @Autowired
    IUserService userService;

    // Creates a new user
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }
}
