package oc.rental.rental_oc.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import oc.rental.rental_oc.constant.ValidationMessages;
import oc.rental.rental_oc.dto.RentalDto;
import oc.rental.rental_oc.dto.request.RentalRequest;
import oc.rental.rental_oc.dto.response.RentalResponse;
import oc.rental.rental_oc.dto.response.RentalsResponse;
import oc.rental.rental_oc.service.RentalService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rentals")
@Validated
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public RentalsResponse getRentals() {
        return rentalService.findAll();
    }

    @GetMapping("{id}")
    public RentalDto getRental(@PathVariable("id") @Positive(message = ValidationMessages.POSITIVE_NUMBER_REQUIRED) Integer id) {
        return rentalService.getRental(id);
    }

    @PostMapping
    public RentalResponse createRental(@Valid @RequestBody RentalRequest rentalRequest, Principal principal) {
        return rentalService.createRental(rentalRequest, principal.getName());
    }
}
