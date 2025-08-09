package oc.rental.rental_oc.constant;

public final class ValidationMessages {

    // Generic messages
    public static final String REQUIRED_FIELD = "Required field";
    public static final String INVALID_FORMAT = "Invalid format";
    public static final String POSITIVE_NUMBER_REQUIRED = "Must be greater than zero";

    // Specific messages
    public static final String EMAIL_ALREADY_USED = "Email already used";
    public static final String RENTAL_NAME_LENGTH = "Name must be between 1 and 255 characters";
    public static final String RENTAL_PICTURE_LENGTH = "The picture must be between 1 and 255 characters";
    public static final String RENTAL_DESCRIPTION_LENGTH = "The description must be equal or below 2000 characters";

    // Authentication messages
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String INVALID_URL_FORMAT = "Invalid URL format";
    public static final String DECIMAL_MAX_VALUE = "Must be less than or equal to 999999.99";

    private ValidationMessages() {}
}