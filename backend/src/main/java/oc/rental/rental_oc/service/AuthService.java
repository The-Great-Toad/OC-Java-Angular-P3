package oc.rental.rental_oc.service;

import jakarta.validation.Valid;
import oc.rental.rental_oc.dto.auth.AuthResponse;
import oc.rental.rental_oc.dto.auth.LoginRequest;
import oc.rental.rental_oc.dto.auth.RegisterRequest;

public interface AuthService {

    /**
     * Registers a new user with the provided registration details.
     *
     * @param registerRequest the registration request containing user details
     * @return an AuthResponse containing the authentication token
     */
    AuthResponse register(RegisterRequest registerRequest);

    /**
     * Logs in a user with the provided login credentials.
     *
     * @param loginRequest the login request containing user credentials
     * @return an AuthResponse containing the authentication token
     */
    AuthResponse login(@Valid LoginRequest loginRequest);
}
