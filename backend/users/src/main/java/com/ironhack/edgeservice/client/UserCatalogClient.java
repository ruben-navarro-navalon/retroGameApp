package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("userCatalog-service")
public interface UserCatalogClient {

    @GetMapping("/platforms")
    List<PlatformDTO> getAll();

    @GetMapping("/platforms/{shortName}")
    PlatformDTO findByShortName(@PathVariable String shortName);

    @PostMapping("/users")
    UserOfCatalogDTO createNewUser(@RequestBody UserOfCatalogDTO userOfCatalogDTO);

    @GetMapping("/collection/{id}")
    CollectionDTO getCollectionById(@PathVariable Long id);

    @PutMapping("/collection/{id}/add")
    CollectionDTO addGameToCollection(@PathVariable Long id, @RequestBody Long apiId);

    @PutMapping("/collection/{id}/remove")
    CollectionDTO removeGameFromCollection(@PathVariable Long id, @RequestBody Long apiId);

    @GetMapping("/wanted/{id}")
    WantedDTO getWantedById(@PathVariable Long id);

    @PutMapping("/wanted/{id}/add")
    WantedDTO addGameToWanted(@PathVariable Long id, @RequestBody Long apiId);

    @PutMapping("/wanted/{id}/remove")
    WantedDTO removeGameFromWanted(@PathVariable Long id, @RequestBody Long apiId);

    @PutMapping("/wanted/{id}/move")
    CollectionDTO moveGameFromWantedToCollection(@PathVariable Long id,@RequestBody Long apiId);

    @GetMapping("/sell/{id}")
    SellDTO getSellById(@PathVariable Long id);

    @PutMapping("/sell/{id}/add")
    SellDTO addGameToSell(@PathVariable Long id, @RequestBody Long apiId);

    @PutMapping("/sell/{id}/remove")
    SellDTO removeGameFromSell(@PathVariable Long id, @RequestBody Long apiId);

    @GetMapping("/people/wants/{apiId}")
    List<String[]> getPeopleWhoWantsWhatISell(@PathVariable Long apiId);

    @GetMapping("/people/sells/{apiId}")
    List<String[]> getPeopleWhoSellsWhatIWant(@PathVariable Long apiId);

}
