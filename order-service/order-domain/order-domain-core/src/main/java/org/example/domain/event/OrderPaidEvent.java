package org.example.domain.event;

import java.time.ZonedDateTime;

import org.example.domain.entity.Order;

import lombok.Getter;


@Getter
public class OrderPaidEvent extends OrderEvent {
	public OrderPaidEvent(Order order, ZonedDateTime createAt) {
		super(order, createAt);
	}
}
