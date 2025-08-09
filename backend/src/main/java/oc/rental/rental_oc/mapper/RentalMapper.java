package oc.rental.rental_oc.mapper;

import oc.rental.rental_oc.dto.RentalDto;
import oc.rental.rental_oc.dto.request.RentalRequest;
import oc.rental.rental_oc.entity.Rental;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentalMapper {

    /**
     * Maps a Rental entity to a RentalDto.
     *
     * @param rental the Rental entity to map
     * @return a RentalDto containing the mapped data
     */
    public RentalDto mapToRentalDto(Rental rental) {
        RentalDto rentalDto = new RentalDto()
                .addId(rental.getId())
                .addName(rental.getName())
                .addSurface(rental.getSurface())
                .addPrice(rental.getPrice())
                .addPicture(rental.getPicture())
                .addDescription(rental.getDescription())
                .addOwnerId(rental.getOwnerId());

        if (!Objects.isNull(rental.getCreatedAt())) {
            rentalDto.addCreatedAt(rental.getCreatedAt().toString());
        }

        if (!Objects.isNull(rental.getUpdatedAt())) {
            rentalDto.addUpdatedAt(rental.getUpdatedAt().toString());
        }

        return rentalDto;
    }

    /**
     * Maps a RentalRequest to a Rental entity.
     *
     * @param rentalRequest the RentalRequest containing rental details
     * @return a Rental entity populated with the details from the request
     */
    public Rental mapToRental(RentalRequest rentalRequest) {
        return new Rental()
                .addName(rentalRequest.name())
                .addSurface(rentalRequest.surface())
                .addPrice(rentalRequest.price())
                .addPicture(rentalRequest.picture())
                .addDescription(rentalRequest.description());
    }

    /**
     * Updates an existing Rental entity with details from a RentalRequest.
     *
     * @param rental the Rental entity to update
     * @param rentalRequest the RentalRequest containing updated rental details
     */
    public void updateRentalFromRequest(Rental rental, RentalRequest rentalRequest) {
        rental
                .addName(rentalRequest.name())
                .addSurface(rentalRequest.surface())
                .addPrice(rentalRequest.price())
                .addPicture(rentalRequest.picture())
                .addDescription(rentalRequest.description());
    }
}
