package com.moser.repositories;

import com.moser.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Juliano Moser
 */
public interface GameRepository extends JpaRepository<Game, Long> {
}
