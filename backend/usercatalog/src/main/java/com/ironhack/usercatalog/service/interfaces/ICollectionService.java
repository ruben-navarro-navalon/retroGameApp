package com.ironhack.usercatalog.service.interfaces;

import com.ironhack.usercatalog.dto.CollectionDTO;

import java.security.Principal;

public interface ICollectionService {
    CollectionDTO getCollectionById(Long id);
    CollectionDTO addGameToCollection(Long id, Long apiId);
    CollectionDTO removeGameFromCollection(Long id, Long apiId);
}
