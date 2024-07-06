package com.github.sirdx.restaurantapp.reservation;

import com.github.sirdx.restaurantapp.customer.CustomerService;
import com.github.sirdx.restaurantapp.email.EmailService;
import com.github.sirdx.restaurantapp.reservation.dto.ReservationRequestDto;
import com.github.sirdx.restaurantapp.table.exception.TableNotFoundException;
import com.github.sirdx.restaurantapp.table.dto.TableResponseDto;
import com.github.sirdx.restaurantapp.table.TableService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CustomerService customerService;
    private final TableService tableService;
    private final EmailService emailService;

    public UUID createReservation(ReservationRequestDto requestDto) {
        var customer = customerService.findById(requestDto.customerId());
        var table = tableService.findById(requestDto.tableId());

        if (!isTableAvailable(requestDto.tableId(), requestDto.startTimestamp(), requestDto.endTimestamp())) {
            throw new TableNotFoundException(
                    String.format("Table %s is not available on this interval", requestDto.tableId())
            );
        }

        var reservation = reservationRepository.save(
                reservationMapper.toReservation(requestDto)
        );

        var customerName = customer.firstName() + " " + customer.lastName();

        try {
            emailService.sendReservationConfirmationEmail(
                    customer.email(),
                    customerName,
                    reservation.getId(),
                    table,
                    requestDto.startTimestamp(),
                    requestDto.endTimestamp()
            );
        } catch (MessagingException e) {

        }

        return reservation.getId();
    }

    public List<TableResponseDto> findAvailableTables(Instant startTimestamp, Instant endTimestamp) {
        return tableService.findAll().stream()
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
