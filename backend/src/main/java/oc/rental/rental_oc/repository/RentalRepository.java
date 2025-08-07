package oc.rental.rental_oc.repository;

import oc.rental.rental_oc.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
