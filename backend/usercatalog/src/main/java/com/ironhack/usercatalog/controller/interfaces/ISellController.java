package com.ironhack.usercatalog.controller.interfaces;

import com.ironhack.usercatalog.dto.SellDTO;

import java.util.List;

public interface ISellController {
    SellDTO getSellById(Long id);
    SellDTO addGameToSell(Long id, Long apiId);
    SellDTO removeGameFromSell(Long id, Long apiId);

    List<String[]> getPeopleWhoSellsWhatIWant(Long apiId);
}
