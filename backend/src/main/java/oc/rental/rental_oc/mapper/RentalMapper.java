package oc.rental.rental_oc.mapper;

import oc.rental.rental_oc.dto.RentalDto;
import oc.rental.rental_oc.dto.request.RentalRequest;
import oc.rental.rental_oc.entity.Rental;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentalMapper {

    /**
     * Map Rental entity to RentalDto.
     *
     * @param rental the Rental entity to be mapped
     * @return RentalDto containing the mapped data
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
     * Map RentalRequest to Rental entity.
     *
     * @param rentalRequest the request containing the details of the rental to be created
     * @param picturePath the path to the rental picture
     * @return Rental entity populated with the request details
     */
    public Rental mapToRental(RentalRequest rentalRequest, String picturePath) {
        return new Rental()
                .addName(rentalRequest.name())
                .addSurface(rentalRequest.surface())
                .addPrice(rentalRequest.price())
                .addPicture(picturePath)
                .addDescription(rentalRequest.description());
    }

    /**
     * Update an existing Rental entity with data from RentalRequest.
     *
     * @param rental the Rental entity to be updated
     * @param rentalRequest the request containing the updated details of the rental
     */
    public void updateRentalFromRequest(Rental rental, RentalRequest rentalRequest) {
        rental
                .addName(rentalRequest.name())
                .addSurface(rentalRequest.surface())
                .addPrice(rentalRequest.price())
                .addDescription(rentalRequest.description());
    }
}