package org.example.domain.ports.outputs.message.publisher.payment;

import org.example.domain.entity.Order;
import org.example.domain.event.OrderCreatedEvent;
import org.example.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {


    @Override
    public void publish(OrderCreatedEvent domainEvent);
}
