package oc.rental.rental_oc.service;

import oc.rental.rental_oc.dto.response.RentalsResponse;

public interface RentalService {

    /**
     * Finds all rentals.
     * @return RentalsResponse containing a list of all rentals.
     */
    RentalsResponse findAll();
}
