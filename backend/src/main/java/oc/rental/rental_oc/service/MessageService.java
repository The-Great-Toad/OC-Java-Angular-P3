package oc.rental.rental_oc.service;

import jakarta.validation.Valid;
import oc.rental.rental_oc.dto.request.MessageRequest;
import oc.rental.rental_oc.dto.response.MessageResponse;
import oc.rental.rental_oc.entity.User;

public interface MessageService {
    /**
     * Creates a new message based on the provided request.
     *
     * @param loggedUser the authenticated user
     * @param messageRequest the request containing the details of the message to be created
     * @return a confirmation response
     */
    MessageResponse createMessage(User loggedUser, @Valid MessageRequest messageRequest);
}
