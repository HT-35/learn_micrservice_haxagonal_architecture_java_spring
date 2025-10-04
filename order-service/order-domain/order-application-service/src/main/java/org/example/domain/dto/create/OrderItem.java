package org.example.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.domain.valueobject.ProductId;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
	private final ProductId productId;
	private final int quantity;
	private final BigDecimal price;
	private final BigDecimal subTotal;


}
