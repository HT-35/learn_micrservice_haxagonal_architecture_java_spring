package org.example.domain.ports;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.event.OrderCreatedEvent;
import org.example.domain.ports.outputs.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreatedEventApplicationListener {
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;


    @TransactionalEventListener
    void process(OrderCreatedEvent orderCreatedEvent) {}

}
