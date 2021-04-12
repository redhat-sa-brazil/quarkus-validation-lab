package org.leonardo.guestbook.shell.amqp.inbound;

import org.leonardo.guestbook.shell.amqp.GuestRegisteredDto;
import org.leonardo.guestbook.usecases.MakeGuestIdenticonById;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class EventReceiver implements RabbitListenerConfigurer {

    private static final String SUCCESSFULLY_REACTED_TO_EVENT_MSG =
        "Successfully reacted to 'guest-registered': id={}";

    private static final String FAILED_TO_REACT_TO_EVENT_MSG =
        "Failed to react to 'guest-registered': id='{}' details='{}'";

    private static final Logger logger = getLogger(EventReceiver.class);

    private final MakeGuestIdenticonById makeGuestIdenticonById;

    public EventReceiver(MakeGuestIdenticonById makeGuestIdenticonById) {
        this.makeGuestIdenticonById = makeGuestIdenticonById;
    }

    @Override
    public void configureRabbitListeners(
        RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) { }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void handleGuestRegistered(GuestRegisteredDto guestRegisteredDto) {
        String id = guestRegisteredDto.getGuestId();
        try {
            makeGuestIdenticonById.execute(id);
            logger.info(SUCCESSFULLY_REACTED_TO_EVENT_MSG, id);
        } catch (Throwable e) {
            logger.error(FAILED_TO_REACT_TO_EVENT_MSG, id, e.getMessage());
        }
    }
}
