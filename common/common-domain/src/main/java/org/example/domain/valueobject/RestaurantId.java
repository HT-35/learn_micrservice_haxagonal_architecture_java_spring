package org.example.domain.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RestaurantId extends BaseId<UUID> {
	public RestaurantId(UUID value) {
		super(value);
	}
}
