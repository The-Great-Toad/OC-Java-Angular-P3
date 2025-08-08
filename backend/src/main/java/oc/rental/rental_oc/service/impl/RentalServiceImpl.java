package oc.rental.rental_oc.service.impl;

import oc.rental.rental_oc.constant.ErrorMessages;
import oc.rental.rental_oc.dto.RentalDto;
import oc.rental.rental_oc.dto.request.RentalRequest;
import oc.rental.rental_oc.dto.response.RentalResponse;
import oc.rental.rental_oc.dto.response.RentalsResponse;
import oc.rental.rental_oc.entity.Rental;
import oc.rental.rental_oc.exception.RentalException;
import oc.rental.rental_oc.exception.RentalNotFoundException;
import oc.rental.rental_oc.mapper.RentalMapper;
import oc.rental.rental_oc.repository.RentalRepository;
import oc.rental.rental_oc.service.AuthService;
import oc.rental.rental_oc.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalServiceImpl.class);
    private static final String LOG_PREFIX = "[RentalServiceImpl]";

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final AuthService authService;

    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper, AuthService authService) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.authService = authService;
    }

    @Override
    public RentalsResponse findAll() {
        LOGGER.debug("{} - Finding all rentals", LOG_PREFIX);
        List<Rental> rentals = rentalRepository.findAll();

        if (rentals.isEmpty()) {
            LOGGER.debug("{} - No rentals found", LOG_PREFIX);
            return new RentalsResponse().withEmptyRentals();
        }
        LOGGER.debug("{} - Found {} rentals", LOG_PREFIX, rentals.size());

        return  new RentalsResponse()
                .addRentals(rentals.stream()
                        .map(rentalMapper::mapToRentalDto)
                        .toList());
    }

    @Override
    public RentalDto getRental(Integer id) {
        LOGGER.debug("{} - Getting rental by id {}", LOG_PREFIX, id);
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException(ErrorMessages.RENTAL_NOT_FOUND));

        LOGGER.debug("{} - Found rental: {}", LOG_PREFIX, rental);
        return rentalMapper.mapToRentalDto(rental);
    }

    @Override
    public RentalResponse createRental(RentalRequest rentalRequest, String ownerName) {
        LOGGER.debug("{} - Rental creation request received: {}", LOG_PREFIX, rentalRequest);
        try {
            /* Retrieving owner id */
            Integer ownerId = authService.getUserIdByUsername(ownerName);
            LOGGER.debug("{} - Owner ID for username {}: {}", LOG_PREFIX, ownerName, ownerId);

            /* Saving mapped Rental entity */
            Rental rental = rentalMapper.mapToRental(rentalRequest)
                    .addOwnerId(ownerId);

            Rental savedRental = rentalRepository.save(rental);
            LOGGER.info("{} - Rental created successfully with ID: {}", LOG_PREFIX, savedRental.getId());

            return new RentalResponse("Rental created successfully");

        } catch (DataAccessException e) {
            String errorMessage = "Database error while creating rental";
            LOGGER.error("{} - {}: {}", LOG_PREFIX, errorMessage, e.getMessage());
            throw new RentalException(errorMessage, e);

        } catch (UsernameNotFoundException e) {
            String errorMessage = "Invalid username: " + ownerName;
            LOGGER.error("{} - {}", LOG_PREFIX, errorMessage);
            throw new RentalException(errorMessage, e);
        }
    }
}
