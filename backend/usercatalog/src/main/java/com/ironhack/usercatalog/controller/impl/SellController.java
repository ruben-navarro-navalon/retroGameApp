package com.ironhack.usercatalog.controller.impl;

import com.ironhack.usercatalog.controller.interfaces.ISellController;
import com.ironhack.usercatalog.dto.SellDTO;
import com.ironhack.usercatalog.service.interfaces.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SellController implements ISellController {
    @Autowired
    ISellService sellService;


    // Returns the sell list from a user.
    @GetMapping("/sell/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SellDTO getSellById(@PathVariable Long id) {
        return sellService.getSellById(id);
    }

    // Adds a game to the sell list of a user.
    @PutMapping("/sell/{id}/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SellDTO addGameToSell(@PathVariable Long id, @RequestBody Long apiId) {
        return sellService.addGameToSell(id, apiId);
    }

    // Removes a game from the sell list of a user.
    @PutMapping("/sell/{id}/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SellDTO removeGameFromSell(@PathVariable Long id, @RequestBody Long apiId) {
        return sellService.removeGameFromSell(id, apiId);
    }

    // Returns a list of people who sells games what user wants.
    @GetMapping("/people/sells/{apiId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String[]> getPeopleWhoSellsWhatIWant(@PathVariable Long apiId) {
        return sellService.getPeopleWhoSellsWhatIWant(apiId);
    }
}
