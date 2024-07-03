package com.github.sirdx.restaurantapp.reservation;

import com.github.sirdx.restaurantapp.reservation.dto.ReservationRequestDto;
import com.github.sirdx.restaurantapp.reservation.dto.ReservationResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {
    public Reservation toReservation(ReservationRequestDto requestDto) {
        return Reservation.builder()
                .customerId(requestDto.customerId())
                .tableId(requestDto.tableId())
                .startTimestamp(requestDto.startTimestamp())
                .endTimestamp(requestDto.endTimestamp())
                .build();
    }

    public ReservationResponseDto fromReservation(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getCustomerId(),
                reservation.getTableId(),
                reservation.getStartTimestamp(),
                reservation.getEndTimestamp(),
                reservation.getCreatedAt()
        );
    }
}
