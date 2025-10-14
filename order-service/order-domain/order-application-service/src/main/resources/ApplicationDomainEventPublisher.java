package org.example.domain.ports;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.event.OrderEvent;
import org.example.domain.event.publisher.DomainEventPublisher;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderEvent> {

    // This is a Spring component that publishes domain events using Spring's ApplicationEventPublisher
    // ex: OrderCreatedEvent, OrderCancelledEvent, etc.
    // It implements DomainEventPublisher interface to publish domain events
    // and ApplicationEventPublisherAware to get the ApplicationEventPublisher instance from Spring
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("Published order event: {}", domainEvent.getOrder().getId().getValue());
    }


}
