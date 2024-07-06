package com.github.sirdx.restaurantapp.email;

import lombok.Getter;

public enum EmailTemplates {
    RESERVATION_CONFIRMATION("reservation-confirmation.html", "Reservation confirmation");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
