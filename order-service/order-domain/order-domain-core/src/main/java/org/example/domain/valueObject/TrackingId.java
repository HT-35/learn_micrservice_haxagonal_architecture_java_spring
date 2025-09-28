package org.example.domain.valueObject;

import java.util.UUID;

import org.example.domain.valueobject.BaseId;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TrackingId extends BaseId<UUID> {
	public TrackingId(UUID value) {
		super(value);
	}
}
