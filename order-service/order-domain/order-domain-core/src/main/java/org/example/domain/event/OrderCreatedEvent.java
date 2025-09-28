package org.example.domain.event;

import java.time.ZonedDateTime;

import org.example.domain.entity.Order;

import lombok.Getter;


@Getter
public class OrderCreatedEvent extends OrderEvent  {
	public OrderCreatedEvent(Order order, ZonedDateTime createAt) {
		super(order, createAt);
	}
}
