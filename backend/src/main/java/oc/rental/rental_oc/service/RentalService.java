package oc.rental.rental_oc.service;

import oc.rental.rental_oc.dto.RentalDto;
import oc.rental.rental_oc.dto.request.RentalRequest;
import oc.rental.rental_oc.dto.response.RentalResponse;
import oc.rental.rental_oc.dto.response.RentalsResponse;

import java.security.Principal;

public interface RentalService {

    /**
     * Finds all rentals.
     * @return RentalsResponse containing a list of all rentals.
     */
    RentalsResponse findAll();

    /**
     * Retrieves a rental by its ID.
     * @param id the ID of the rental to retrieve.
     * @return RentalDto containing the rental details.
     */
    RentalDto getRental(Integer id);

    /**
     * Creates a new rental based on the provided request.
     *
     * @param rentalRequest the request containing rental details.
     * @param ownerName the name of the owner creating the rental
     * @return RentalResponse containing the creation confirmation message
     */
    RentalResponse createRental(RentalRequest rentalRequest, String ownerName);

    /**
     * Updates an existing rental.
     *
     * @param id the ID of the rental to update
     * @param rentalRequest the request containing updated rental details
     * @param principal the rental owner
     * @return RentalResponse containing the update confirmation message
     */
    RentalResponse updateRental(Integer id, RentalRequest rentalRequest, Principal principal);
}
