package com.github.sirdx.restaurantapp.reservation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record ReservationRequestDto(
        @NotNull(message = "Customer is required")
        UUID customerId,

        @NotNull(message = "Table is required")
        UUID tableId,

        @NotNull(message = "Reservation start date is required")
        @Future(message = "Reservation start timestamp must be in the future")
        Instant startTimestamp,

        @NotNull(message = "Reservation end date is required")
        @Future(message = "Reservation end timestamp must be in the future")
        Instant endTimestamp
) {

}
