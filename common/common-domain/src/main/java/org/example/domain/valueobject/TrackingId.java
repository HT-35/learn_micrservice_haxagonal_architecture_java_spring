package org.example.domain.valueobject;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TrackingId extends BaseId<UUID> {
	public TrackingId(UUID value) {
		super(value);
	}
}
