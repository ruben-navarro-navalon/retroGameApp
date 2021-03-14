package com.ironhack.usercatalog.controller.impl;

import com.ironhack.usercatalog.controller.interfaces.IPlatformDataController;
import com.ironhack.usercatalog.model.PlatformData;
import com.ironhack.usercatalog.service.interfaces.IPlatformDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlatformDataController implements IPlatformDataController {
    @Autowired
    IPlatformDataService platformService;

    // Gets platform data to fill the navbar and the platform info.


    @GetMapping("/platforms")
    @ResponseStatus(HttpStatus.OK)
    public List<PlatformData> getAll() {
        return platformService.getAll();
    }

    @GetMapping("/platforms/{shortName}")
    @ResponseStatus(HttpStatus.OK)
    public PlatformData findByShortName(@PathVariable String shortName) {
        return platformService.findByShortName(shortName);
    }
}
