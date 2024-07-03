package com.github.sirdx.restaurantapp.kafka.reservation;

import java.time.Instant;
import java.util.UUID;

public record Customer(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Instant createdAt
) {

}
