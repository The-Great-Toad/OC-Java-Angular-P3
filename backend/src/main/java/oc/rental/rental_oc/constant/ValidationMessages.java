package oc.rental.rental_oc.constant;

public final class ValidationMessages {

    // Generic messages
    public static final String REQUIRED_FIELD = "{validation.field.required}";
    public static final String INVALID_FORMAT = "{validation.format.invalid}";
    public static final String POSITIVE_NUMBER_REQUIRED = "{validation.positive.number.required}";

    // Specific messages
    public static final String EMAIL_ALREADY_USED = "{validation.email.already.used}";
    public static final String RENTAL_NAME_LENGTH = "{validation.rental.name.length}";
    public static final String RENTAL_PICTURE_LENGTH = "{validation.rental.picture.length}";
    public static final String RENTAL_DESCRIPTION_LENGTH = "{validation.rental.description.length}";

    // Authentication messages
    public static final String INVALID_CREDENTIALS = "{validation.credentials.invalid}";

    private ValidationMessages() {
        /* Prevent implementation */
    }
}