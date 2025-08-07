package oc.rental.rental_oc.dto.response;

import oc.rental.rental_oc.dto.RentalDto;

import java.util.List;
import java.util.StringJoiner;

public class RentalsResponse {

    private List<RentalDto> rentals;

    public List<RentalDto> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalDto> rentals) {
        this.rentals = rentals;
    }

    public RentalsResponse addRentals(List<RentalDto> rentals) {
        this.rentals = rentals;
        return this;
    }

    public RentalsResponse withEmptyRentals() {
        this.rentals = List.of();
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RentalsResponse.class.getSimpleName() + "[", "]")
                .add("rentals=" + rentals)
                .toString();
    }
}
