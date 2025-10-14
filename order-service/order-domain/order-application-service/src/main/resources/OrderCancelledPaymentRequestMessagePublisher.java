package org.example.domain.ports.outputs.message.publisher.payment;

import org.example.domain.event.OrderCreatedEvent;
import org.example.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
    @Override
    public void publish(OrderCreatedEvent domainEvent);
}
