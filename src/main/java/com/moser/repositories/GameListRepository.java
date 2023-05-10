package com.moser.repositories;

import com.moser.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Juliano Moser
 */
public interface GameListRepository extends JpaRepository<GameList, Long> {
}
