package oc.rental.rental_oc.mapper;

import oc.rental.rental_oc.dto.RentalDto;
import oc.rental.rental_oc.dto.request.RentalRequest;
import oc.rental.rental_oc.entity.Rental;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentalMapper {

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

    public Rental mapToRental(RentalRequest rentalRequest, String picturePath) {
        return new Rental()
                .addName(rentalRequest.name())
                .addSurface(rentalRequest.surface())
                .addPrice(rentalRequest.price())
                .addPicture(picturePath)
                .addDescription(rentalRequest.description());
    }

    public void updateRentalFromRequest(Rental rental, RentalRequest rentalRequest, String picturePath) {
        rental
                .addName(rentalRequest.name())
                .addSurface(rentalRequest.surface())
                .addPrice(rentalRequest.price())
                .addDescription(rentalRequest.description());

        // Ne met à jour la picture que si un nouveau fichier est fourni
        if (picturePath != null) {
            rental.addPicture(picturePath);
        }
    }
}