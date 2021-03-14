package com.ironhack.usercatalog.service.interfaces;

import com.ironhack.usercatalog.model.PlatformData;

import java.util.List;

public interface IPlatformDataService {
    List<PlatformData> getAll();
    PlatformData findByShortName(String shortName);
}
