package org.example.domain.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public class CustomerId extends BaseId<UUID> {
	public CustomerId(UUID value) {
		super(value);
	}
}
