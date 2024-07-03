package com.github.sirdx.restaurantapp.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationProducer {
    private final KafkaTemplate<String, ReservationConfirmationDto> kafkaTemplate;

    public void sendReservationConfirmation(ReservationConfirmationDto reservationConfirmationDto) {
        log.info("Sending reservation confirmation");

        Message<ReservationConfirmationDto> message = MessageBuilder
                .withPayload(reservationConfirmationDto)
                .setHeader(KafkaHeaders.TOPIC, "reservation-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
