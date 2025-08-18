package oc.rental.rental_oc.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import oc.rental.rental_oc.constant.ValidationMessages;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record RentalRequest(
        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        @Length(min = 1, max = 255, message = ValidationMessages.RENTAL_NAME_LENGTH)
        String name,

        @NotNull(message = ValidationMessages.REQUIRED_FIELD)
        @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
        Double surface,

        @NotNull(message = ValidationMessages.REQUIRED_FIELD)
        @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
        @DecimalMax(value = "999999.99", message = ValidationMessages.DECIMAL_MAX_VALUE)
        BigDecimal price,

        MultipartFile picture, // null pour les updates

        @Length(max = 2000, message = ValidationMessages.RENTAL_DESCRIPTION_LENGTH)
        String description
) {}

