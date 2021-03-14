package com.ironhack.usercatalog.service.interfaces;

import com.ironhack.usercatalog.dto.SellDTO;

import java.util.List;

public interface ISellService {
    SellDTO getSellById(Long id);
    SellDTO addGameToSell(Long id, Long apiId);
    SellDTO removeGameFromSell(Long id, Long apiId);

    List<String[]> getPeopleWhoSellsWhatIWant(Long apiId);
}
