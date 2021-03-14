package com.ironhack.usercatalog.controller.impl;

import com.ironhack.usercatalog.controller.interfaces.ICollectionController;
import com.ironhack.usercatalog.dto.CollectionDTO;
import com.ironhack.usercatalog.repository.CollectionRepository;
import com.ironhack.usercatalog.service.interfaces.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CollectionController implements ICollectionController {
    @Autowired
    ICollectionService collectionService;
    @Autowired
    CollectionRepository collectionRepository;

    // Returns a collection by id
    @GetMapping("/collection/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CollectionDTO getCollectionById(@PathVariable Long id) {
        return collectionService.getCollectionById(id);
    }

    // Adds a game to a collection
    @PutMapping("/collection/{id}/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CollectionDTO addGameToCollection(@PathVariable Long id, @RequestBody Long apiId) {
        return collectionService.addGameToCollection(id, apiId);
    }

    // Removes a game from a collection
    @PutMapping("/collection/{id}/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CollectionDTO removeGameFromCollection(@PathVariable Long id, @RequestBody Long apiId) {
        return collectionService.removeGameFromCollection(id, apiId);
    }
}
