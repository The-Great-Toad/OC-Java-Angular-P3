package oc.rental.rental_oc.repository;

import oc.rental.rental_oc.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT r FROM Rental r JOIN User u ON r.ownerId = u.id WHERE r.id = :id AND u.email = :username")
    Optional<Rental> findByIdAndOwnerUsername(@Param("id") Integer id, @Param("username") String username);
}
