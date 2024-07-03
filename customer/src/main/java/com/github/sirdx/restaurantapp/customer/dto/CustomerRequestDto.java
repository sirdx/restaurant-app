package com.github.sirdx.restaurantapp.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerRequestDto(
        @NotNull(message = "Customer first name is required")
        @Size(min = 1, max = 30, message = "Customer first name must be between 1 and 30 characters")
        String firstName,

        @NotNull(message = "Customer last name is required")
        @Size(min = 1, max = 30, message = "Customer last name must be between 1 and 30 characters")
        String lastName,

        @NotNull(message = "Customer email is required")
        @Email(message = "Customer email is invalid")
        @Size(max = 70, message = "Customer email must be at most 70 characters")
        String email
) {

}
