package oc.rental.rental_oc.config;

import jakarta.validation.ConstraintViolationException;
import oc.rental.rental_oc.constant.ErrorMessages;
import oc.rental.rental_oc.exception.MessageException;
import oc.rental.rental_oc.exception.RentalException;
import oc.rental.rental_oc.exception.RentalNotFoundException;
import oc.rental.rental_oc.exception.StorageException;
import oc.rental.rental_oc.exception.TokenGenerationException;
import oc.rental.rental_oc.exception.TokenValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String LOGGER_PREFIX = "[GlobalExceptionHandler]";
    public static final String TIMESTAMP = "timestamp";
    public static final String LOG_MESSAGE_FORMAT = "{} - {}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            LOGGER.error("{} - Validation error: {} - {}", LOGGER_PREFIX, error.getField(), error.getDefaultMessage());
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            int dotIndex = field.indexOf(".");
            if (dotIndex != -1) {
                field = field.substring(dotIndex + 1);
            }
            LOGGER.error("{} - Constraint violation: {} - {}", LOGGER_PREFIX, field, violation.getMessage());
            errors.put(field, violation.getMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Class<?> requiredType = e.getRequiredType();
        String typeInfo = Objects.nonNull(requiredType)
                ? "Expected type: %s".formatted(requiredType.getSimpleName())
                : "";
        String message = String.format("Invalid value '%s' for parameter '%s'. %s", e.getValue(), e.getName(), typeInfo);
        LOGGER.error(LOG_MESSAGE_FORMAT, LOGGER_PREFIX, message);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        problemDetail.setTitle("Type Mismatch Error");
        problemDetail.setProperty(TIMESTAMP, Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException e) {
        LOGGER.error("{} - Bad Credentials : {}", LOGGER_PREFIX, e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setTitle("Invalid Credentials");
        problemDetail.setProperty(TIMESTAMP, Instant.now());
        return problemDetail;
    }

    @ExceptionHandler({TokenGenerationException.class, TokenValidationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleTokenException(Exception e) {
        String errorType = e instanceof TokenValidationException ? "Token Validation Error" : "Token Generation Error";
        LOGGER.error("{} - {}: {}", LOGGER_PREFIX, errorType, e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle(errorType);
        problemDetail.setProperty(TIMESTAMP, Instant.now());
        return problemDetail;
    }

    @ExceptionHandler({RentalNotFoundException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNotFoundException(Exception e) {
        String errorType = e instanceof RentalNotFoundException ? "Rental Not Found" : "User Not Found";
        LOGGER.error("{} - {}: {}", LOGGER_PREFIX, errorType, e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle(errorType);
        problemDetail.setProperty(TIMESTAMP, Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(RentalException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleRentalException(RentalException e) {
        LOGGER.info(LOG_MESSAGE_FORMAT, LOGGER_PREFIX, e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setTitle("Rental Exception");
        problemDetail.setProperty(TIMESTAMP, Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleStorageException(StorageException e) {
        LOGGER.info(LOG_MESSAGE_FORMAT, LOGGER_PREFIX, e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("Storage Exception");
        problemDetail.setProperty(TIMESTAMP, Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(MessageException.class)
    public ProblemDetail handleMessageException(MessageException e) {
        HttpStatus httpStatus = e.getMessage().contains(ErrorMessages.MESSAGE_DB_ERROR) ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.BAD_REQUEST;
        LOGGER.error(LOG_MESSAGE_FORMAT, LOGGER_PREFIX, e.getMessage());
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, e.getMessage());
        problemDetail.setTitle("Message Exception");
        problemDetail.setProperty(TIMESTAMP, Instant.now());
        return problemDetail;
    }
}
