package org.example.domain.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProductId extends BaseId<UUID> {
	public ProductId(UUID value) {
		super(value);
	}
}
