package org.example.domain.valueobject;

import java.util.UUID;

import lombok.Getter;

@Getter
public class OrderId extends BaseId<UUID> {
	public OrderId(UUID value) {
		super(value);
	}
}
