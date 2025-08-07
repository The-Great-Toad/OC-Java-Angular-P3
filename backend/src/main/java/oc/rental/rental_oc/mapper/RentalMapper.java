package oc.rental.rental_oc.mapper;

import oc.rental.rental_oc.dto.RentalDto;
import oc.rental.rental_oc.entity.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    /**
     * Maps a Rental entity to a RentalDto.
     *
     * @param rental the Rental entity to map
     * @return a RentalDto containing the mapped data
     */
    public RentalDto mapToRentalDto(Rental rental) {
        return new RentalDto()
                .addId(rental.getId())
                .addName(rental.getName())
                .addSurface(rental.getSurface())
                .addPrice(rental.getPrice())
                .addPicture(rental.getPicture())
                .addDescription(rental.getDescription())
                .addOwnerId(rental.getOwnerId())
                .addCreatedAt(rental.getCreatedAt().toString())
                .addUpdatedAt(rental.getUpdatedAt().toString());
    }
}
