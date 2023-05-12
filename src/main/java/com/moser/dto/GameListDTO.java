package com.moser.dto;

import com.moser.entities.GameList;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Setter
@Getter
public class GameListDTO {

    @Schema(example = "1", minimum = "1", maximum = "2147483647")
    private Long id;
    @Schema(example = "Aventura e RPG", pattern = "([A-Za-z0-9])", format = "binary")
    private String name;
}
