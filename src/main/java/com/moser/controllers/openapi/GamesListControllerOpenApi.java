package com.moser.controllers.openapi;

import com.moser.dto.GameDTO;
import com.moser.dto.GameListDTO;
import com.moser.dto.GameMinDTO;
import com.moser.dto.ReplacementDTO;
import com.moser.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Juliano Moser
 */
@Tag(name = "GamesList")
public interface GamesListControllerOpenApi {

    @PageableParameter
    @Operation(summary = "Lista os games")
    Page<GameListDTO> findAll(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca uma lista por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrado", content = {@Content(schema =
            @Schema(ref = "Problem"))})
    })
    List<GameMinDTO> findGames(@Parameter(description = "ID de uma lista", example = "1", required = true)
                      Long listId);

    @Operation(summary = "Altera ordem da lista")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "Lista alterada"))
    void move(@Parameter(description = "ID de uma lista", example = "1", required = true)
              Long listId,
              @RequestBody(description = "Representação do index do source e target", required = true)
              ReplacementDTO replacementDTO);
}
