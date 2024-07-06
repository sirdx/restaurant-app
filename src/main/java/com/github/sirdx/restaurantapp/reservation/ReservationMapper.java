package com.github.sirdx.restaurantapp.reservation;

import com.github.sirdx.restaurantapp.customer.CustomerRepository;
import com.github.sirdx.restaurantapp.reservation.dto.ReservationRequestDto;
import com.github.sirdx.restaurantapp.reservation.dto.ReservationResponseDto;
import com.github.sirdx.restaurantapp.table.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationMapper {
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;

    public Reservation toReservation(ReservationRequestDto requestDto) {
        var customer = customerRepository.findById(requestDto.customerId())
                .orElse(null);

        var table = tableRepository.findById(requestDto.tableId())
                .orElse(null);

        return Reservation.builder()
                .customer(customer)
                .table(table)
                .startTimestamp(requestDto.startTimestamp())
                .endTimestamp(requestDto.endTimestamp())
                .build();
    }

    public ReservationResponseDto fromReservation(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getCustomer().getId(),
                reservation.getTable().getId(),
                reservation.getStartTimestamp(),
                reservation.getEndTimestamp(),
                reservation.getCreatedAt()
        );
    }
}
