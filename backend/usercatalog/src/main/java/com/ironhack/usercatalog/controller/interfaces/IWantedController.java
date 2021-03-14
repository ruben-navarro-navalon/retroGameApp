package com.ironhack.usercatalog.controller.interfaces;

import com.ironhack.usercatalog.dto.CollectionDTO;
import com.ironhack.usercatalog.dto.WantedDTO;

import java.util.List;

public interface IWantedController {
    WantedDTO getWantedById(Long id);
    WantedDTO addGameToWanted(Long id, Long apiId);
    WantedDTO removeGameFromWanted(Long id, Long apiId);
    CollectionDTO moveGameFromWantedToCollection(Long id, Long apiId);

    List<String[]> getPeopleWhoWantsWhatISell(Long apiId);
}
