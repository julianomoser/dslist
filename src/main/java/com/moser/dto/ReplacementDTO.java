package com.moser.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juliano Moser
 */
@Getter
@Setter
public class ReplacementDTO {

    @NotNull
    @Positive
    private Integer sourceIndex;
    @NotNull
    @PositiveOrZero
    private Integer targetIndex;
}
