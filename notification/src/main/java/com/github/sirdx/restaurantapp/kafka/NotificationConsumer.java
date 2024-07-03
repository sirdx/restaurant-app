package com.github.sirdx.restaurantapp.kafka;

import com.github.sirdx.restaurantapp.email.EmailService;
import com.github.sirdx.restaurantapp.kafka.reservation.ReservationConfirmationDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "reservation-topic")
    public void consumeReservationConfirmationNotification(
            ReservationConfirmationDto reservationConfirmationDto
    ) throws MessagingException {
        log.info("Consuming reservation confirmation");

        var customerName = reservationConfirmationDto.customer().firstName() + " " + reservationConfirmationDto.customer().lastName();
        emailService.sendReservationConfirmationEmail(
                reservationConfirmationDto.customer().email(),
                customerName,
                reservationConfirmationDto.id(),
                reservationConfirmationDto.table(),
                reservationConfirmationDto.startTimestamp(),
                reservationConfirmationDto.endTimestamp()
        );
    }
}
