package com.github.sirdx.restaurantapp.kafka.reservation;

import java.time.Instant;
import java.util.UUID;

public record ReservationConfirmationDto(
        UUID id,
        Table table,
        Customer customer,
        Instant startTimestamp,
        Instant endTimestamp,
        Instant createdAt
) {

}
