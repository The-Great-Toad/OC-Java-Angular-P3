package oc.rental.rental_oc.exception;

import java.io.Serial;

public class MessageException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
