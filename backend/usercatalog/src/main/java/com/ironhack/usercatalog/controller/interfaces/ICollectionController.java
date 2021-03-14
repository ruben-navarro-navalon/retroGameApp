package com.ironhack.usercatalog.controller.interfaces;

import com.ironhack.usercatalog.dto.CollectionDTO;

public interface ICollectionController {
    CollectionDTO getCollectionById(Long id);
    CollectionDTO addGameToCollection(Long id, Long apiId);
    CollectionDTO removeGameFromCollection(Long id, Long apiId);
}
