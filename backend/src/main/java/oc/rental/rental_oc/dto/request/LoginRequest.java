package oc.rental.rental_oc.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import oc.rental.rental_oc.constant.ValidationMessages;

public record LoginRequest(
        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        @Email(message = ValidationMessages.INVALID_FORMAT)
        String email,

        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        String password
) {}
