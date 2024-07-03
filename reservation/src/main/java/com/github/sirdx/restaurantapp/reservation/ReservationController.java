package com.github.sirdx.restaurantapp.reservation;

import com.github.sirdx.restaurantapp.reservation.dto.ReservationRequestDto;
import com.github.sirdx.restaurantapp.table.TableResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<UUID> createReservation(
            @RequestBody @Valid ReservationRequestDto requestDto
    ) {
        return ResponseEntity.ok(reservationService.createReservation(requestDto));
    }

    @GetMapping("/available-tables")
    public ResponseEntity<List<TableResponseDto>> getAvailableTables(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Valid @Future Instant startTimestamp,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Valid @Future Instant endTimestamp
    ) {
        return ResponseEntity.ok(reservationService.findAvailableTables(startTimestamp, endTimestamp));
    }
}
