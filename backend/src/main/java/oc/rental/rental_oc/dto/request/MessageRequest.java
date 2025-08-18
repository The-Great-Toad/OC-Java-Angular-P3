package oc.rental.rental_oc.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import oc.rental.rental_oc.constant.ValidationMessages;
import org.hibernate.validator.constraints.Length;

public record MessageRequest(
        @JsonProperty("rental_id")
        @NotNull(message = ValidationMessages.REQUIRED_FIELD)
        @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
        Integer rentalId,

        @JsonProperty("user_id")
        @NotNull(message = ValidationMessages.REQUIRED_FIELD)
        @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED)
        Integer userId,

        @NotBlank(message = ValidationMessages.REQUIRED_FIELD)
        @Length(max = 2000, message = ValidationMessages.MESSAGE_MAX_LENGTH)
        String message
) {}
