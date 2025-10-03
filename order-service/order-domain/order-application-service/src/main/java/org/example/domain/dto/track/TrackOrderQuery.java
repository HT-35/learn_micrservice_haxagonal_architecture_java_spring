package org.example.domain.dto.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TrackOrderQuery {
	private final String orderTrackingId;
}
