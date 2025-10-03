package org.example.domain.dto.track;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.example.domain.valueobject.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
	@NotNull
	private final UUID orderTrackingId;
	@NotNull
	private final OrderStatus orderStatus;
	private final List<String> message;
}
