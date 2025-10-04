package org.example.domain.dto.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.example.domain.entity.OrderItem;
import org.example.domain.valueobject.CustomerId;
import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.RestaurantId;

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
