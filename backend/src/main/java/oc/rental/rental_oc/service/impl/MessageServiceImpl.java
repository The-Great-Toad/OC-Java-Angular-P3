package oc.rental.rental_oc.service.impl;

import oc.rental.rental_oc.constant.ErrorMessages;
import oc.rental.rental_oc.constant.SuccessMessages;
import oc.rental.rental_oc.dto.request.MessageRequest;
import oc.rental.rental_oc.dto.response.MessageResponse;
import oc.rental.rental_oc.entity.Message;
import oc.rental.rental_oc.entity.Rental;
import oc.rental.rental_oc.entity.User;
import oc.rental.rental_oc.exception.MessageException;
import oc.rental.rental_oc.mapper.MessageMapper;
import oc.rental.rental_oc.repository.MessageRepository;
import oc.rental.rental_oc.service.MessageService;
import oc.rental.rental_oc.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private static final String LOGGER_PREFIX = "MessageServiceImpl";

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final RentalService rentalService;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper, RentalService rentalService) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.rentalService = rentalService;
    }

    @Override
    public MessageResponse createMessage(User loggedUser, MessageRequest messageRequest) {
        LOGGER.info("{} - New message request from: {}", LOGGER_PREFIX, messageRequest);
        validateMessage(loggedUser, messageRequest);
        return saveMessage(messageRequest);
    }

    /**
     * Validates the message request against the logged user and rental.
     *
     * @param loggedUser the user who is sending the message
     * @param messageRequest the message request containing rental and user IDs
     * @throws MessageException if validation fails
     */
    private void validateMessage(User loggedUser, MessageRequest messageRequest) {
        /* Validate that the rental exists */
        Rental rental = rentalService.getRentalIfExists(messageRequest.rentalId());

        /* Check for user impersonation */
        if (!Objects.equals(loggedUser.getId(), messageRequest.userId())) {
            LOGGER.error("User {} does not match the message request user {}", loggedUser.getId(), messageRequest.userId());
            throw new MessageException(ErrorMessages.MESSAGE_USER_IMPERSONATED);
        }

        /* Validate user permission to send a message to this rental */
        if (Objects.equals(loggedUser.getId(), rental.getOwnerId())) {
            LOGGER.error("User {} is the owner of rental {}, cannot send message", loggedUser.getId(), rental.getId());
            throw new MessageException(ErrorMessages.MESSAGE_USER_IS_OWNER);
        }
    }

    /**
     * Saves the message to the database and returns a response.
     *
     * @param messageRequest the message request containing the details of the message to be saved
     * @return MessageResponse containing the success message
     * @throws MessageException if there is an error saving the message
     */
    private MessageResponse saveMessage(MessageRequest messageRequest) {
        try {
            Message savedMessage = messageRepository.save(messageMapper.mapToMessage(messageRequest));
            LOGGER.info("{} - Message saved successfully: {}", LOGGER_PREFIX, savedMessage);
            return new MessageResponse(SuccessMessages.MESSAGE_SUCCESS);

        } catch (DataAccessException e) {
            final String errorMessage = ErrorMessages.MESSAGE_DB_ERROR.formatted(e.getMessage());
            LOGGER.error("{} - {}", LOGGER_PREFIX, errorMessage);
            throw new MessageException(errorMessage, e);
        }
    }
}
