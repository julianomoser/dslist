package com.moser.services;

import com.moser.dto.GameListDTO;
import com.moser.repositories.GameListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Service
public class GameListService {

    private final GameListRepository gameListRepository;

    public GameListService(GameListRepository gameRepository) {
        this.gameListRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        var result = gameListRepository.findAll();
        return result.stream().map(GameListDTO::new).toList();
    }
}
