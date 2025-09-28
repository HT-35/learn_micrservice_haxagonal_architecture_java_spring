package org.example.domain.event;

import java.time.ZonedDateTime;

import org.example.domain.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public abstract class OrderEvent implements DomainEvent<Order> {
	private Order order;
	private ZonedDateTime createAt;
}
