package com.moser.assembler;


import com.moser.dto.GameMinDTO;
import com.moser.entities.Game;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Component
public class GamesModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GameMinDTO toModel(Game game) {
        return modelMapper.map(game, GameMinDTO.class);
    }

    public List<GameMinDTO> toCollectionModel(List<Game> games) {
        return games.stream()
                .map(game -> toModel(game))
                .collect(Collectors.toList());
    }
}
