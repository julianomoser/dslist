package com.moser.services;

import com.moser.dto.GameListDTO;
import com.moser.entities.GameList;
import com.moser.projections.GameMinProjection;
import com.moser.repositories.GameListRepository;
import com.moser.repositories.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Juliano Moser
 */
@Service
public class GameListService {

    private final GameListRepository gameListRepository;
    private final GameRepository gameRepository;

    public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
        this.gameListRepository = gameListRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public Page<GameList> findAll(Pageable pageable) {
        return gameListRepository.findAll(pageable);
    }

    @Transactional
    public void movie(Long listId, int sourceIndex, int targetIndex) {
        List<GameMinProjection> gamesList = gameRepository.searchByList(listId);

        var sourceGame = gamesList.remove(sourceIndex);
        gamesList.add(targetIndex, sourceGame);

        IntStream.rangeClosed(Math.min(sourceIndex, targetIndex), Math.max(sourceIndex, targetIndex))
                .forEachOrdered(idx -> gameListRepository.updateBelongingPosition(listId, gamesList.get(idx).getId(), idx));

    }
}
