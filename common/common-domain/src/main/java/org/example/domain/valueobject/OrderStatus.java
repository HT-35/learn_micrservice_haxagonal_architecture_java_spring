package org.example.domain.valueobject;

import lombok.Getter;

@Getter
public enum OrderStatus {
	PENDING,
	PAID,
	APPROVED,
	CANCELLING,
	CANCELLED
}
