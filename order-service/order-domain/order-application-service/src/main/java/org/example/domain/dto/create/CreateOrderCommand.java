package org.example.domain.dto.create;

import java.util.List;

import org.example.domain.entity.OrderItem;
import org.example.domain.valueobject.CustomerId;
import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.RestaurantId;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@AllArgsConstructor
@Getter
public class CreateOrderCommand {

	@NotNull

	private final CustomerId customerId;

	@NotNull
	private final RestaurantId restaurantId;

	@NotNull
	private final Money price;

	@NotNull
	private final List<OrderItem> items;

	@NotNull
	private final OrderAddress address;
}
