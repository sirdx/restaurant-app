package com.github.sirdx.restaurantapp.email;

import com.github.sirdx.restaurantapp.table.dto.TableResponseDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendReservationConfirmationEmail(
            String to,
            String customerName,
            UUID reservationId,
            TableResponseDto table,
            Instant startTimestamp,
            Instant endTimestamp
    ) throws MessagingException {
        var mimeMessage = mailSender.createMimeMessage();
        var mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name()
        );
        mimeMessageHelper.setFrom("contact@projectsiitp.pl");

        final String templateName = EmailTemplates.RESERVATION_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("reservationId", reservationId);
        variables.put("tableLocation", table.location());
        variables.put("tableDescription", table.description());
        variables.put("tableSeats", table.seats());
        variables.put("startTimestamp", startTimestamp);
        variables.put("endTimestamp", endTimestamp);

        var context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(EmailTemplates.RESERVATION_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);
            mimeMessageHelper.setTo(to);
            mailSender.send(mimeMessage);
            log.info(String.format("Email successfully sent to %s", to));
        } catch (MessagingException e) {
            log.warn(String.format("Email could not be sent to %s", to), e);
        }
    }
}
