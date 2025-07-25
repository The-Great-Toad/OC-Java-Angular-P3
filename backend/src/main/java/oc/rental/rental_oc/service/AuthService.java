package oc.rental.rental_oc.service;

import oc.rental.rental_oc.dto.auth.AuthResponse;
import oc.rental.rental_oc.dto.auth.RegisterRequest;

public interface AuthService {

    /**
     * Registers a new user with the provided registration details.
     *
     * @param registerRequest the registration request containing user details
     * @return an AuthResponse containing the authentication token
     */
    AuthResponse register(RegisterRequest registerRequest);
}
