package oc.rental.rental_oc.constant;

public class ErrorMessages {

    public static final String USER_NOT_FOUND = "User not found";

    public static final String RENTAL_NOT_FOUND = "Rental not found";
    public static final String RENTAL_UPDATE_UNAUTHORIZED = "Rental update unauthorized";

    public static final String MESSAGE_USER_IMPERSONATED = "User does not match the message request";
    public static final String MESSAGE_USER_IS_OWNER = "User is the owner of the rental, cannot send message";
    public static final String MESSAGE_DB_ERROR = "Error saving message: %s";

    private ErrorMessages() {}
}
