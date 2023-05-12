package com.moser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ReplacementDTO {

    @Schema(example = "3", minimum = "0", maximum = "2147483647")
    @NotNull
    @Positive
    private Integer sourceIndex;
    @Schema(example = "1", minimum = "0", maximum = "2147483647")
    @NotNull
    @PositiveOrZero
    private Integer targetIndex;
}
