package com.moser.services;

import com.moser.dto.GameDTO;
import com.moser.dto.GameMinDTO;
import com.moser.entities.Game;
import com.moser.exception.GameNaoEncontradoException;
import com.moser.projections.GameMinProjection;
import com.moser.repositories.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long gameId) {
        Game game = findOrFail(gameId);
        return new GameDTO(game);
    }

    @Transactional(readOnly = true)
    public Page<Game> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByGameList(Long listId) {
        List<GameMinProjection> games = gameRepository.searchByList(listId);
        return games.stream().map(GameMinDTO::new).toList();
    }

    public Game findOrFail(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNaoEncontradoException(gameId));
    }
}
