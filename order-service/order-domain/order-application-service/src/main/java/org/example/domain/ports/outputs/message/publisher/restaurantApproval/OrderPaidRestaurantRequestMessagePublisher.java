package org.example.domain.ports.outputs.message.publisher.restaurantApproval;

//import org.example.domain.event.OrderCreatedEvent;

import org.example.domain.event.OrderCreatedEvent;
import org.example.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
    @Override
    public void publish(OrderCreatedEvent domainEvent);
}
