package oc.rental.rental_oc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import oc.rental.rental_oc.constant.ValidationMessages;
import org.hibernate.validator.constraints.Length;

public record RentalRequest(
        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        @Length(min = 1, max = 255, message = ValidationMessages.RENTAL_NAME_LENGTH)
        String name,

        @NotNull(message = ValidationMessages.REQUIRED_FIELD)
        @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
        Double surface,

        @NotNull(message = ValidationMessages.REQUIRED_FIELD)
        @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
        Double price,

        @Length(min = 1, max = 255, message = ValidationMessages.RENTAL_PICTURE_LENGTH)
        String picture,

        @Length(max = 2000, message = ValidationMessages.RENTAL_DESCRIPTION_LENGTH)
        String description
) {}

