package org.example.domain.dto.create;

import java.util.UUID;

import org.example.domain.valueobject.OrderStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderRes {
	@NotNull
	private final UUID orderTrackingId;
	@NotNull
	private final OrderStatus orderStatus;
	@NotNull
	private final String message;
}
