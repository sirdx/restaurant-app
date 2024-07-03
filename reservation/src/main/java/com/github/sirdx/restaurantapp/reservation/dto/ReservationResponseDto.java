package com.github.sirdx.restaurantapp.reservation.dto;

import java.time.Instant;
import java.util.UUID;

public record ReservationResponseDto(
        UUID id,
        UUID customerId,
        UUID tableId,
        Instant startTimestamp,
        Instant endTimestamp,
        Instant createdAt
) {

}
