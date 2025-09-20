package org.example.domain.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode(callSuper = true)
public class StreetAddress extends BaseId<UUID> {

	private String street;
	private String postalCode;
	private String city;

	public StreetAddress(UUID value, String street, String postalCode, String city) {
		super(value);
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
}
