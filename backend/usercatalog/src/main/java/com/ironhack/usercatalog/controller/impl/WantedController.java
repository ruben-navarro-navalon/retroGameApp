package com.ironhack.usercatalog.controller.impl;

import com.ironhack.usercatalog.controller.interfaces.IWantedController;
import com.ironhack.usercatalog.dto.CollectionDTO;
import com.ironhack.usercatalog.dto.WantedDTO;
import com.ironhack.usercatalog.model.Wanted;
import com.ironhack.usercatalog.service.interfaces.ICollectionService;
import com.ironhack.usercatalog.service.interfaces.IWantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WantedController implements IWantedController {
    @Autowired
    IWantedService wantedService;

    // Retuns the wanted list of a user.
    @GetMapping("/wanted/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WantedDTO getWantedById(@PathVariable Long id) {
        return wantedService.getWantedById(id);
    }

    // Adds a game to the wanted list of a user.
    @PutMapping("/wanted/{id}/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public WantedDTO addGameToWanted(@PathVariable Long id, @RequestBody Long apiId) {
        return wantedService.addGameToWanted(id, apiId);
    }

    // Removes a game from the wanted list of a user.
    @PutMapping("/wanted/{id}/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public WantedDTO removeGameFromWanted(@PathVariable Long id, @RequestBody Long apiId) {
        return wantedService.removeGameFromWanted(id, apiId);
    }

    // Moves a game from the wanted list of a user to the collection.
    @PutMapping("/wanted/{id}/move")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CollectionDTO moveGameFromWantedToCollection(@PathVariable Long id, @RequestBody Long apiId) {
        return wantedService.moveGameFromWantedToCollection(id, apiId);
    }

    // Returns a list of people who wants what user sell.
    @GetMapping("/people/wants/{apiId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String[]> getPeopleWhoWantsWhatISell(@PathVariable Long apiId) {
        return wantedService.getPeopleWhoWantsWhatISell(apiId);
    }


}
