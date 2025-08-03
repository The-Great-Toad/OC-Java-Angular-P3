package oc.rental.rental_oc.exception;

import java.io.Serial;

public class TokenValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TokenValidationException(String message) {
        super(message);
    }

    public TokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
