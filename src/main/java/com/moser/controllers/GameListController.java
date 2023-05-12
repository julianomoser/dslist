package com.moser.controllers;

import com.moser.assembler.GamesListModelAssembler;
import com.moser.controllers.openapi.GamesListControllerOpenApi;
import com.moser.dto.GameListDTO;
import com.moser.dto.GameMinDTO;
import com.moser.dto.ReplacementDTO;
import com.moser.entities.GameList;
import com.moser.services.GameListService;
import com.moser.services.GameService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Juliano Moser
 */
@RestController
@RequestMapping(path = "/lists", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameListController implements GamesListControllerOpenApi {

    private final GameListService gameListService;
    private final GameService gameService;
    private final GamesListModelAssembler gamesListModelAssembler;

    public GameListController(GameListService gameService, GameService gameService1, GamesListModelAssembler gamesListModelAssembler) {
        this.gameListService = gameService;
        this.gameService = gameService1;
        this.gamesListModelAssembler = gamesListModelAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public Page<GameListDTO> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<GameList> gamesListPage = gameListService.findAll(pageable);
        List<GameListDTO> gamesListDTO = gamesListModelAssembler.toCollectionModel(gamesListPage.getContent());
        return new PageImpl<>(gamesListDTO, pageable, gamesListPage.getTotalElements());
    }

    @GetMapping(path = "/{listId}/games", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<GameMinDTO> findGames(@PathVariable Long listId) {
        return gameService.findByGameList(listId);
    }

    @PostMapping(path = "/{listId}/replacement")
    @Override
    public void move(@PathVariable Long listId,
                     @RequestBody @Valid ReplacementDTO replacementDTO) {
        gameListService.movie(listId, replacementDTO.getSourceIndex(), replacementDTO.getTargetIndex());
    }
}
