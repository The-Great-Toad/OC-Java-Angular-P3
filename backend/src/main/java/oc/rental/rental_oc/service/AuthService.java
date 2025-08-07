package oc.rental.rental_oc.service;

import jakarta.validation.Valid;
import oc.rental.rental_oc.dto.UserDto;
import oc.rental.rental_oc.dto.response.AuthResponse;
import oc.rental.rental_oc.dto.request.LoginRequest;
import oc.rental.rental_oc.dto.request.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface AuthService extends UserDetailsService {

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


    /**
     * Retrieves the details of the currently authenticated user.
     *
     * @param principal the security principal representing the authenticated user
     * @return a UserDto containing user details
     */
    UserDto getUserDetails(Principal principal);
}
