package oc.rental.rental_oc.constant;

public final class ValidationMessages {

    // Generic messages
    public static final String REQUIRED_FIELD = "{validation.field.required}";
    public static final String INVALID_FORMAT = "{validation.format.invalid}";

    // Specific messages
    public static final String EMAIL_ALREADY_USED = "{validation.email.already.used}";

    // Authentication messages
    public static final String INVALID_CREDENTIALS = "{validation.credentials.invalid}";

    private ValidationMessages() {
        /* Prevent implementation */
    }
}