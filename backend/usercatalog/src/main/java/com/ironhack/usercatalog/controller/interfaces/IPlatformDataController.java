package com.ironhack.usercatalog.controller.interfaces;

import com.ironhack.usercatalog.model.PlatformData;

import java.util.List;

public interface IPlatformDataController {
    List<PlatformData> getAll();
    PlatformData findByShortName(String shortName);
}
