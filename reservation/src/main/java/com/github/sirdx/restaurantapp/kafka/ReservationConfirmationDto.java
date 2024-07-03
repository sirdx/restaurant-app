package com.github.sirdx.restaurantapp.kafka;

import com.github.sirdx.restaurantapp.customer.CustomerResponseDto;
import com.github.sirdx.restaurantapp.table.TableResponseDto;

import java.time.Instant;
import java.util.UUID;

public record ReservationConfirmationDto(
        UUID id,
        TableResponseDto table,
        CustomerResponseDto customer,
        Instant startTimestamp,
        Instant endTimestamp,
        Instant createdAt
) {

}
