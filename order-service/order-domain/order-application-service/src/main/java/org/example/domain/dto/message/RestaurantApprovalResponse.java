package org.example.domain.dto.message;

import java.util.List;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.example.domain.valueobject.OrderApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantApprovalResponse {
	private String id;
	private String sagaId;
	private String orderId;
	private String restaurantId;
	private Instance createdAt;
	private OrderApprovalStatus orderApprovalStatus;
	private List<String> failureMessages;
}
