package com.github.sirdx.restaurantapp.table.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TableRequestDto(
        @NotNull(message = "Table location is required")
        @Size(min = 1, max = 8, message = "Table location must be between 1 and 8 characters")
        String location,

        @NotNull(message = "Table description is required")
        @Size(min = 1, max = 200, message = "Table description must be between 1 and 8 characters")
        String description,

        @NotNull(message = "Table seats are required")
        @Positive(message = "Table seats should be positive")
        Integer seats
) {

}
