package oc.rental.rental_oc.controller;

import oc.rental.rental_oc.dto.response.RentalsResponse;
import oc.rental.rental_oc.service.RentalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public RentalsResponse getRentals() {
        return this.rentalService.findAll();
    }
}
