package com.ironhack.usercatalog.service.impl;

import com.ironhack.usercatalog.model.*;
import com.ironhack.usercatalog.repository.CollectionRepository;
import com.ironhack.usercatalog.repository.SellRepository;
import com.ironhack.usercatalog.repository.UserRepository;
import com.ironhack.usercatalog.repository.WantedRepository;
import com.ironhack.usercatalog.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    WantedRepository wantedRepository;
    @Autowired
    SellRepository sellRepository;

    // Creates a new user
    public User createNewUser(User user) {
        userRepository.save(user);

        List<Game> games = new ArrayList<>();

        collectionRepository.save(new Collection(user, games));
        wantedRepository.save(new Wanted(user,games));
        sellRepository.save(new Sell(user,games));
        return user;
    }
}
