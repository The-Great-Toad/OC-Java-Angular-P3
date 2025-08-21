package oc.rental.rental_oc.controller;

import jakarta.validation.constraints.Positive;
import oc.rental.rental_oc.constant.ValidationMessages;
import oc.rental.rental_oc.dto.UserDto;
import oc.rental.rental_oc.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED) Integer id) {
        return ResponseEntity.ok(authService.getUserById(id));
    }

}
