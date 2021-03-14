package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.client.UserCatalogClient;
import com.ironhack.edgeservice.dto.*;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.repository.UserRepository;
import com.ironhack.edgeservice.service.interfaces.IUserCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCatalogService implements IUserCatalogService {
    @Autowired
    UserCatalogClient userCatalogClient;
    @Autowired
    UserRepository userRepository;

    public List<PlatformDTO> getAll() {
        return userCatalogClient.getAll();
    }

    public PlatformDTO findByShortName(String shortName) {
        return userCatalogClient.findByShortName(shortName);
    }

    public UserOfCatalogDTO createNewUser(UserOfCatalogDTO userOfCatalogDTO){
        return userCatalogClient.createNewUser(userOfCatalogDTO);
    }

    public CollectionDTO getCollectionById(Long id) {
        return userCatalogClient.getCollectionById(id);
    }

    public CollectionDTO addGameToCollection(Long apiId, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return userCatalogClient.addGameToCollection(user.get().getId(), apiId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

    public CollectionDTO removeGameFromCollection(Long apiId, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return userCatalogClient.removeGameFromCollection(user.get().getId(), apiId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

    public WantedDTO getWantedById(Long id) {
        return userCatalogClient.getWantedById(id);
    }

    public WantedDTO addGameToWanted(Long apiId, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return userCatalogClient.addGameToWanted(user.get().getId(), apiId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

    public WantedDTO removeGameFromWanted(Long apiId, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return userCatalogClient.removeGameFromWanted(user.get().getId(), apiId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

    public CollectionDTO moveGameFromWantedToCollection(Long apiId, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return userCatalogClient.moveGameFromWantedToCollection(user.get().getId(), apiId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

    public SellDTO getSellById(Long id) {
        return userCatalogClient.getSellById(id);
    }

    public SellDTO addGameToSell(Long apiId, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return userCatalogClient.addGameToSell(user.get().getId(), apiId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }

    public SellDTO removeGameFromSell(Long apiId, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isPresent()) {
            return userCatalogClient.removeGameFromSell(user.get().getId(), apiId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
        }
    }





    public List<SellerOrBuyerDTO> getPeopleWhoWantsWhatISell(Long apiId) {
        List<String[]> people = userCatalogClient.getPeopleWhoWantsWhatISell(apiId);
        List<SellerOrBuyerDTO> sellerOrBuyerDTOList = new ArrayList<>();
        for(String[] row : people) {
            List<String[]> stateAndTown = userRepository.findStateTownAndEmailByUsername(row[0]);
            sellerOrBuyerDTOList.add(new SellerOrBuyerDTO(row, stateAndTown.get(0), "buy"));
        }
        return sellerOrBuyerDTOList;
    }

    public List<SellerOrBuyerDTO> getPeopleWhoSellsWhatIWant(Long apiId) {
        List<String[]> people = userCatalogClient.getPeopleWhoSellsWhatIWant(apiId);
        List<SellerOrBuyerDTO> sellerOrBuyerDTOList = new ArrayList<>();
        for(String[] row : people) {
            List<String[]> stateAndTown = userRepository.findStateTownAndEmailByUsername(row[0]);
            sellerOrBuyerDTOList.add(new SellerOrBuyerDTO(row, stateAndTown.get(0), "sell"));
        }
        return sellerOrBuyerDTOList;
    }
}
