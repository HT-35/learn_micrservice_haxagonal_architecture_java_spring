package org.example.domain.valueobject;

import java.util.UUID;

import lombok.Getter;

@Getter
public class CustomerId extends BaseId<UUID> {
	public CustomerId(UUID value) {
		super(value);
	}
}
