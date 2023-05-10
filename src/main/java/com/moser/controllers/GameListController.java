package com.moser.controllers;

import com.moser.dto.GameListDTO;
import com.moser.dto.GameMinDTO;
import com.moser.services.GameListService;
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
@RequestMapping(path = "/lists")
public class GameListController {

    private final GameListService gameListService;
    private final GameService gameService;

    public GameListController(GameListService gameService, GameService gameService1) {
        this.gameListService = gameService;
        this.gameService = gameService1;
    }

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @GetMapping(path = "/{listId}/games")
    public List<GameMinDTO> findGames(@PathVariable Long listId) {
        return gameService.findByGameList(listId);
    }
}
