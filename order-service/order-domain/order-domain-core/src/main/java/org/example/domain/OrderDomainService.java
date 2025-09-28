package org.example.domain;

import java.util.List;

import org.example.domain.entity.Order;
import org.example.domain.entity.Restaurant;
import org.example.domain.event.OrderCreatedEvent;
import org.example.domain.event.OrderPaidEvent;

public interface OrderDomainService {
	OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

	OrderPaidEvent payOrder(Order order);

	void approveOrder(Order order);

	void cancelOrderPayment(Order order, List<String> failureMessages);


	void cancelOrder(Order order, List<String> failureMessages);
}
