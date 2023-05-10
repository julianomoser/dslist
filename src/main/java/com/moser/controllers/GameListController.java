package com.moser.controllers;

import com.moser.dto.GameDTO;
import com.moser.dto.GameListDTO;
import com.moser.dto.GameMinDTO;
import com.moser.services.GameListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/lists")
public class GameListController {

    private final GameListService gameListService;

    public GameListController(GameListService gameService) {
        this.gameListService = gameService;
    }

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }
}
