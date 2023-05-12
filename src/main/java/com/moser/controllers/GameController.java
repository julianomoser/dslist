package com.moser.controllers;

import com.moser.assembler.GamesModelAssembler;
import com.moser.dto.GameDTO;
import com.moser.dto.GameMinDTO;
import com.moser.entities.Game;
import com.moser.services.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    private final GameService gameService;
    private final GamesModelAssembler gamesModelAssembler;

    public GameController(GameService gameService, GamesModelAssembler gamesModelAssembler) {
        this.gameService = gameService;
        this.gamesModelAssembler = gamesModelAssembler;
    }

    @GetMapping(path = "/{gameId}")
    public GameDTO findById(@PathVariable Long gameId) {
        return gameService.findById(gameId);
    }

    @GetMapping
    public Page<GameMinDTO> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<Game> gamesPage = gameService.findAll(pageable);
        List<GameMinDTO> gamesMinDTOList = gamesModelAssembler.toCollectionModel(gamesPage.getContent());
        return new PageImpl<>(gamesMinDTOList, pageable, gamesPage.getTotalElements());
    }
}
