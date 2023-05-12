package com.moser.controllers.openapi;

import com.moser.dto.GameDTO;
import com.moser.dto.GameMinDTO;
import com.moser.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Juliano Moser
 */
@Tag(name = "Games")
public interface GamesControllerOpenApi {

    @PageableParameter
    @Operation(summary = "Lista os games")
    Page<GameMinDTO> findAll(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca um game por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID do game inválido", content = {@Content(schema =
            @Schema(ref = "Problema"))}),
            @ApiResponse(responseCode = "404", description = "Game não encontrado", content = {@Content(schema =
            @Schema(ref = "Problema"))})
    })
    GameDTO findById(@Parameter(description = "ID de um game", example = "1", required = true)
                     Long gameId);
}
