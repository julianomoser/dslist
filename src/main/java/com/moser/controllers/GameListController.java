package com.moser.controllers;

import com.moser.dto.GameListDTO;
import com.moser.dto.GameMinDTO;
import com.moser.dto.ReplacementDTO;
import com.moser.services.GameListService;
import com.moser.services.GameService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/{listId}/replacement")
    public void move(@PathVariable Long listId,
                     @RequestBody @Valid ReplacementDTO replacementDTO) {
        gameListService.movie(listId, replacementDTO.getSourceIndex(), replacementDTO.getTargetIndex());
    }
}
