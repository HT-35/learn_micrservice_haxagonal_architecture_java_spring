package org.example.domain.entity;

import org.example.domain.valueObject.OrderItemId;
import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.OrderId;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@EqualsAndHashCode(callSuper = true)

public class OrderItem extends BaseEntity<OrderItemId> {

	private OrderId orderId;
	private final Product product;
	// số lượng
	private final Integer quantity;
	// price of product
	private final Money price;
	// subTotal of Product = price * quantity            
	private final Money subTotal;

	// chỉ có thể trong Order khởi tạo, không thể khởi tạo trực tiếp OrderItem
	void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
		this.orderId = orderId;
		super.setId(orderItemId);

	}

	boolean isPriceValid() {
	return price.isGreateThanZero()
					&& price.equals(product.getPrice())
					&& price.multiply(quantity).equals(subTotal);
	}

	public OrderItem(Builder builder) {
		super(builder.orderItemId);
		this.orderId = builder.orderId;
		this.product = builder.product;
		this.quantity = builder.quantity;
		this.price = builder.price;
		this.subTotal = builder.subTotal;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static final class Builder {
		private OrderItemId orderItemId;
		private OrderId orderId;
		private Product product;
		private Integer quantity;
		private Money price;
		private Money subTotal;

	}

}
