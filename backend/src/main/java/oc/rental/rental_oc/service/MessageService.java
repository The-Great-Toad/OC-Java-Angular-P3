package oc.rental.rental_oc.service;

import jakarta.validation.Valid;
import oc.rental.rental_oc.dto.request.MessageRequest;
import oc.rental.rental_oc.dto.response.MessageResponse;

public interface MessageService {
    /**
     * Creates a new message based on the provided request.
     *
     * @param messageRequest the request containing the details of the message to be created
     * @return a confirmation response
     */
    MessageResponse createMessage(@Valid MessageRequest messageRequest);
}
