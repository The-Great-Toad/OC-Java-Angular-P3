package oc.rental.rental_oc.exception;

import java.io.Serial;

public class RentalException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RentalException(String message) {
        super(message);
    }

    public RentalException(String message, Throwable cause) {
        super(message, cause);
    }
}
