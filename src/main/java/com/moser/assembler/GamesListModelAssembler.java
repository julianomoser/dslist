package com.moser.assembler;


import com.moser.dto.GameListDTO;
import com.moser.dto.GameMinDTO;
import com.moser.entities.Game;
import com.moser.entities.GameList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juliano Moser
 */
@Component
public class GamesListModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GameListDTO toModel(GameList game) {
        return modelMapper.map(game, GameListDTO.class);
    }

    public List<GameListDTO> toCollectionModel(List<GameList> games) {
        return games.stream()
                .map(game -> toModel(game))
                .collect(Collectors.toList());
    }
}
