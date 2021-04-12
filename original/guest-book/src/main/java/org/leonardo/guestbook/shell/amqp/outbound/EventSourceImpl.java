package org.leonardo.guestbook.shell.amqp.outbound;

import org.leonardo.guestbook.domain.EventSource;
import org.leonardo.guestbook.shell.amqp.GuestRegisteredDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EventSourceImpl implements EventSource {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public EventSourceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void emitGuestRegistered(String id) {
        GuestRegisteredDto dto = new GuestRegisteredDto(id);
        rabbitTemplate.convertAndSend(exchange, routingkey, dto);
    }
}
