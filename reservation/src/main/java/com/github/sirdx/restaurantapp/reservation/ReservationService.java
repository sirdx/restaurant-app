package com.github.sirdx.restaurantapp.reservation;

import com.github.sirdx.restaurantapp.customer.CustomerClient;
import com.github.sirdx.restaurantapp.exception.CustomerNotFoundException;
import com.github.sirdx.restaurantapp.exception.ReservationNotFoundException;
import com.github.sirdx.restaurantapp.exception.TableNotFoundException;
import com.github.sirdx.restaurantapp.kafka.ReservationConfirmationDto;
import com.github.sirdx.restaurantapp.kafka.ReservationProducer;
import com.github.sirdx.restaurantapp.reservation.dto.ReservationRequestDto;
import com.github.sirdx.restaurantapp.reservation.dto.ReservationResponseDto;
import com.github.sirdx.restaurantapp.table.TableClient;
import com.github.sirdx.restaurantapp.table.TableResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CustomerClient customerClient;
    private final TableClient tableClient;
    private final ReservationProducer reservationProducer;

    public UUID createReservation(ReservationRequestDto requestDto) {
        var customer = customerClient.findCustomerById(requestDto.customerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot make reservation: Customer with %s ID not found", requestDto.customerId())
                ));

        var table = tableClient.getTable(requestDto.tableId())
                .orElseThrow(() -> new TableNotFoundException(
                        String.format("Cannot make reservation: Table with %s ID not found", requestDto.tableId())
                ));

        if (!isTableAvailable(requestDto.tableId(), requestDto.startTimestamp(), requestDto.endTimestamp())) {
            throw new TableNotFoundException(
                    String.format("Table %s is not available on this interval", requestDto.tableId())
            );
        }

        var reservation = reservationRepository.save(
                reservationMapper.toReservation(requestDto)
        );

        reservationProducer.sendReservationConfirmation(
                new ReservationConfirmationDto(
                        reservation.getId(),
                        table,
                        customer,
                        requestDto.startTimestamp(),
                        requestDto.endTimestamp(),
                        reservation.getCreatedAt()
                )
        );

        return reservation.getId();
    }

    public List<TableResponseDto> findAvailableTables(Instant startTimestamp, Instant endTimestamp) {
        return tableClient.getTables().stream()
                .filter(dto -> isTableAvailable(dto.id(), startTimestamp, endTimestamp))
                .toList();
    }

    private boolean isTableAvailable(UUID id, Instant startTimestamp, Instant endTimestamp) {
        var reservations = reservationRepository.findAllByTableId(id);

        for (var reservation : reservations) {
            if (timestampsOverlapse(reservation.getStartTimestamp(), reservation.getEndTimestamp(), startTimestamp, endTimestamp)) {
                return false;
            }
        }

        return true;
    }

    private boolean timestampsOverlapse(Instant startTimestampA, Instant endTimestampA, Instant startTimestampB, Instant endTimestampB) {
        var x1 = startTimestampA.getEpochSecond();
        var x2 = endTimestampA.getEpochSecond();
        var y1 = startTimestampB.getEpochSecond();
        var y2 = endTimestampB.getEpochSecond();

        return Math.max(x1, y1) <= Math.min(x2, y2);
    }
}
