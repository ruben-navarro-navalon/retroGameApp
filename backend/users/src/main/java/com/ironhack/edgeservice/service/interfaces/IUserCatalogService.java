package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.dto.*;

import java.security.Principal;
import java.util.List;

public interface IUserCatalogService {
    List<PlatformDTO> getAll();
    PlatformDTO findByShortName(String shortName);
    UserOfCatalogDTO createNewUser(UserOfCatalogDTO userOfCatalogDTO);

    CollectionDTO getCollectionById(Long id);
    CollectionDTO addGameToCollection(Long apiId, Principal principal);
    CollectionDTO removeGameFromCollection(Long apiId, Principal principal);

    WantedDTO getWantedById(Long id);
    WantedDTO addGameToWanted(Long apiId, Principal principal);
    WantedDTO removeGameFromWanted(Long apiId, Principal principal);
    CollectionDTO moveGameFromWantedToCollection(Long apiId, Principal principal);

    SellDTO getSellById(Long id);
    SellDTO addGameToSell(Long apiId, Principal principal);
    SellDTO removeGameFromSell(Long apiId, Principal principal);

    List<SellerOrBuyerDTO> getPeopleWhoWantsWhatISell(Long apiId);
    List<SellerOrBuyerDTO> getPeopleWhoSellsWhatIWant(Long apiId);
}
