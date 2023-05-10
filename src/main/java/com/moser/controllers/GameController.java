package com.moser.controllers;

import com.moser.dto.GameDTO;
import com.moser.dto.GameMinDTO;
import com.moser.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/games")
public class GameController {

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    private final GameService gameService;

    @GetMapping(value = "/{gameId}")
    public GameDTO findById(@PathVariable Long gameId) {
        return gameService.findById(gameId);
    }

    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }
}
