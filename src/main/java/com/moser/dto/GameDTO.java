package com.moser.dto;

import com.moser.entities.Game;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class GameDTO {

    private Long id;
    private String title;
    private Integer year;
    private String genre;
    private String platforms;
    private Double score;
    private String imgUrl;
    private String shortDescription;
    private String longDescription;

    public GameDTO(Game entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
