package com.moser.dto;

import com.moser.entities.Game;
import com.moser.projections.GameMinProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class GameMinDTO {

    @Schema(example = "1", minimum = "1", maximum = "2147483647")
    private Long id;
    @Schema(example = "Mario", pattern = "([A-Za-z0-9])", format = "binary")
    private String title;
    @Schema(example = "2023")
    private Integer year;
    @Schema(example = "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/6.png", pattern = "([A-Za-z0-9])", format = "binary")
    private String imgUrl;
    @Schema(example = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!", pattern = "([A-Za-z0-9])", format = "binary")
    private String shortDescription;

    public GameMinDTO() {
    }

    public GameMinDTO(Game entity) {
        id = entity.getId();
        title = entity.getTitle();
        year = entity.getYear();
        imgUrl = entity.getImgUrl();
        shortDescription = entity.getShortDescription();
    }

    public GameMinDTO(GameMinProjection projection) {
        id = projection.getId();
        title = projection.getTitle();
        year = projection.getGameYear();
        imgUrl = projection.getImgUrl();
        shortDescription = projection.getShortDescription();
    }
}
