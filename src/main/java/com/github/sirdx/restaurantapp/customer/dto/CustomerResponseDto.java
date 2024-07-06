package com.github.sirdx.restaurantapp.customer.dto;

import java.time.Instant;
import java.util.UUID;

public record CustomerResponseDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Instant createdAt
) {

}
