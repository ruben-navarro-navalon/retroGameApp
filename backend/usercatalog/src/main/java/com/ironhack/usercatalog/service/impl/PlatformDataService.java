package com.ironhack.usercatalog.service.impl;

import com.ironhack.usercatalog.model.PlatformData;
import com.ironhack.usercatalog.repository.PlatformRepository;
import com.ironhack.usercatalog.service.interfaces.IPlatformDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformDataService implements IPlatformDataService {
    @Autowired
    PlatformRepository platformRepository;

    // Gets platform data to fill the navbar and the platform info.

    public List<PlatformData> getAll() {
        return platformRepository.findAll();
    }
    public PlatformData findByShortName(String shortName) {
        return platformRepository.findByShortName(shortName);
    }
}
