package com.github.sirdx.restaurantapp.email;

import lombok.Getter;

@Getter
public enum EmailTemplates {
    RESERVATION_CONFIRMATION("reservation-confirmation.html", "Reservation confirmation");

    private final String template;
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
