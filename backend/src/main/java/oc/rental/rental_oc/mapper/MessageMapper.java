package oc.rental.rental_oc.mapper;

import oc.rental.rental_oc.dto.request.MessageRequest;
import oc.rental.rental_oc.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    /**
     * Map MessageRequest to Message entity.
     *
     * @param messageRequest the request containing the details of the message to be created
     * @return a Message entity populated with the request details
     */
    public Message mapToMessage(MessageRequest messageRequest) {
        return  new Message()
                .addRentalId(messageRequest.rentalId())
                .addUserId(messageRequest.userId())
                .addMessage(messageRequest.message());
    }
}
