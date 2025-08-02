package oc.rental.rental_oc.exception;

import java.io.Serial;

public class TokenGenerationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TokenGenerationException(String message) {
        super(message);
    }

    public TokenGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
