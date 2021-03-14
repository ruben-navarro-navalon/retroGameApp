package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.dto.*;
import com.ironhack.edgeservice.service.interfaces.IUserCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserCatalogController {

    @Autowired
    IUserCatalogService userCatalogService;

    @GetMapping("/platforms")
    public List<PlatformDTO> getAll() {
        return userCatalogService.getAll();
    }

    @GetMapping("/platforms/{shortName}")
    public PlatformDTO findByShortName(@PathVariable String shortName) {
        return userCatalogService.findByShortName(shortName);
    }

    @PostMapping("/users")
    public UserOfCatalogDTO createNewUser(@RequestBody UserOfCatalogDTO userOfCatalogDTO){
        return userCatalogService.createNewUser(userOfCatalogDTO);
    }

    @GetMapping("/collection/{id}")
    public CollectionDTO getCollectionById(@PathVariable Long id) {
        return userCatalogService.getCollectionById(id);
    }

    @PutMapping("collection/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CollectionDTO addGameToCollection(@RequestBody Long apiId, Principal principal) {
        return userCatalogService.addGameToCollection(apiId, principal);
    }

    @PutMapping("collection/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CollectionDTO removeGameFromCollection(@RequestBody Long apiId, Principal principal) {
        return userCatalogService.removeGameFromCollection(apiId, principal);
    }

    @GetMapping("/wanted/{id}")
    public WantedDTO getWantedById(@PathVariable Long id) {
        return userCatalogService.getWantedById(id);
    }

    @PutMapping("wanted/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public WantedDTO addGameToWanted(@RequestBody Long apiId, Principal principal) {
        return userCatalogService.addGameToWanted(apiId, principal);
    }

    @PutMapping("wanted/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public WantedDTO removeGameFromWanted(@RequestBody Long apiId, Principal principal) {
        return userCatalogService.removeGameFromWanted(apiId, principal);
    }

    @PutMapping("/wanted/move")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CollectionDTO moveGameFromWantedToCollection(@RequestBody Long apiId, Principal principal) {
        return userCatalogService.moveGameFromWantedToCollection(apiId, principal);
    }

    @GetMapping("/sell/{id}")
    public SellDTO getSellById(@PathVariable Long id) {
        return userCatalogService.getSellById(id);
    }

    @PutMapping("sell/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SellDTO addGameToSell(@RequestBody Long apiId, Principal principal) {
        return userCatalogService.addGameToSell(apiId, principal);
    }

    @PutMapping("sell/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SellDTO removeGameFromSell(@RequestBody Long apiId, Principal principal) {
        return userCatalogService.removeGameFromSell(apiId, principal);
    }




    @GetMapping("/people/wants/{apiId}")
    @ResponseStatus(HttpStatus.OK)
    public List<SellerOrBuyerDTO> getPeopleWhoWantsWhatISell(@PathVariable Long apiId) {
        return userCatalogService.getPeopleWhoWantsWhatISell(apiId);
    }

    @GetMapping("/people/sells/{apiId}")
    @ResponseStatus(HttpStatus.OK)
    public List<SellerOrBuyerDTO> getPeopleWhoSellsWhatIWant(@PathVariable Long apiId) {
        return userCatalogService.getPeopleWhoSellsWhatIWant(apiId);
    }

}
