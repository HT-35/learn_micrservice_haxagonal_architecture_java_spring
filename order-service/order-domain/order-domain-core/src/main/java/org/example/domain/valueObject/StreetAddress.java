package org.example.domain.valueObject;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public class StreetAddress {

	private final UUID id;
	private final String street;
	private final String postalCode;
	private final String city;

	public StreetAddress(UUID id, String street, String postalCode, String city) {
		this.id = id;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
}
