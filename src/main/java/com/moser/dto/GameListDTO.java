package com.moser.dto;

import com.moser.entities.GameList;
import lombok.Getter;

/**
 * @author Juliano Moser
 */
@Getter
public class GameListDTO {

    private Long id;
    private String name;

    public GameListDTO(GameList entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
