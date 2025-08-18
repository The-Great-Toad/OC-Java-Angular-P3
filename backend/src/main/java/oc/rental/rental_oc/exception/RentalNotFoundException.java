package oc.rental.rental_oc.exception;

import java.io.Serial;

public class RentalNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RentalNotFoundException(String message) {
        super(message);
    }

    public RentalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
