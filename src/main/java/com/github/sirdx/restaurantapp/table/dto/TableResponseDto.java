package com.github.sirdx.restaurantapp.table.dto;

import java.time.Instant;
import java.util.UUID;

public record TableResponseDto(
        UUID id,
        String location,
        String description,
        Integer seats,
        Instant createdAt
) {

}
