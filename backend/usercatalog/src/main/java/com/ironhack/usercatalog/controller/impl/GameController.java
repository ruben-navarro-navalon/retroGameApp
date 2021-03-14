package com.ironhack.usercatalog.controller.impl;

import com.ironhack.usercatalog.controller.interfaces.IGameController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameController implements IGameController {
}
