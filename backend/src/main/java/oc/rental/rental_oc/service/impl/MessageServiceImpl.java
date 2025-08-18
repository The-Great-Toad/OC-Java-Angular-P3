package oc.rental.rental_oc.service.impl;

import oc.rental.rental_oc.constant.ErrorMessages;
import oc.rental.rental_oc.constant.SuccessMessages;
import oc.rental.rental_oc.dto.request.MessageRequest;
import oc.rental.rental_oc.dto.response.MessageResponse;
import oc.rental.rental_oc.entity.Message;
import oc.rental.rental_oc.mapper.MessageMapper;
import oc.rental.rental_oc.repository.MessageRepository;
import oc.rental.rental_oc.service.AuthService;
import oc.rental.rental_oc.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    private static final String LOGGER_PREFIX = "MessageServiceImpl";

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageResponse createMessage(MessageRequest messageRequest) {
        LOGGER.info("{} - New message request from: {}", LOGGER_PREFIX, messageRequest);

        try {
            Message savedMessage = messageRepository.save(messageMapper.mapToMessage(messageRequest));
            LOGGER.info("{} - Message saved successfully: {}", LOGGER_PREFIX, savedMessage);

            return new MessageResponse(SuccessMessages.MESSAGE_SUCCESS);

        } catch (Exception e) {
            LOGGER.error("{} - Error creating message: {}", LOGGER_PREFIX, e.getMessage(), e);
            return new MessageResponse(ErrorMessages.MESSAGE_FAILURE.formatted(e.getMessage()));
        }
    }
}
