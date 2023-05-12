package com.moser.dto;

import com.moser.entities.Game;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class GameDTO {

    @Schema(example = "1", minimum = "1", maximum = "2147483647")
    private Long id;
    @Schema(example = "Mario", pattern = "([A-Za-z0-9])", format = "binary")
    private String title;
    @Schema(example = "2023")
    private Integer year;
    @Schema(example = "Platform", pattern = "([A-Za-z0-9])", format = "binary")
    private String genre;
    @Schema(example = "Super Ness, PC", pattern = "([A-Za-z0-9])", format = "binary")
    private String platforms;
    @Schema(example = "4.7", format = "double", type = "number", minimum = "0", maximum = "1.7976931348623157E+308")
    private Double score;
    @Schema(example = "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/6.png", pattern = "([A-Za-z0-9])", format = "binary")
    private String imgUrl;
    @Schema(example = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!", pattern = "([A-Za-z0-9])", format = "binary")
    private String shortDescription;
    @Schema(example = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum! ...", pattern = "([A-Za-z0-9])", format = "binary")
    private String longDescription;

    public GameDTO(Game entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
