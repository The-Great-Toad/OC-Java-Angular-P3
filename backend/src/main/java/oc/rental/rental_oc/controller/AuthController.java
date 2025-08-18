package oc.rental.rental_oc.controller;

import jakarta.validation.Valid;
import oc.rental.rental_oc.dto.UserDto;
import oc.rental.rental_oc.dto.response.AuthResponse;
import oc.rental.rental_oc.dto.request.LoginRequest;
import oc.rental.rental_oc.dto.request.RegisterRequest;
import oc.rental.rental_oc.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("me")
    public UserDto getUserDetails(Principal principal) {
        return authService.getUserDetails(principal);
    }
}
