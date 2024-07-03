package com.github.sirdx.restaurantapp.kafka.reservation;

import java.time.Instant;
import java.util.UUID;

public record Table(
        UUID id,
        String location,
        String description,
        Integer seats,
        Instant createdAt
) {

}
