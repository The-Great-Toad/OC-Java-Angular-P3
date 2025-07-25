package oc.rental.rental_oc.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import oc.rental.rental_oc.constant.ValidationMessages;
import oc.rental.rental_oc.validation.annotation.EmailNotUsed;

public record RegisterRequest(
        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        String name,

        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        @Email(message = ValidationMessages.INVALID_FORMAT)
        @EmailNotUsed
        String email,

        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        String password
) {}
