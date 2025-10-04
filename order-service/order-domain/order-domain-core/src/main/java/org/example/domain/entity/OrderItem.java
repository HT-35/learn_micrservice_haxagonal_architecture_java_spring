package org.example.domain.entity;

import lombok.*;
import org.example.domain.valueObject.OrderItemId;
import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.OrderId;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    // sản phẩm
    private final Product product;
    // số lượng
    private final Integer quantity;
    // giá tiền của sản phẩm
    private final Money price;
    // thành tiền = price * quantity
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


    @Builder
    public OrderItem(OrderItemId orderItemId,
                     OrderId orderId,
                     Product product,
                     Integer quantity,
                     Money price,
                     Money subTotal) {
        super(orderItemId);
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.subTotal = subTotal;
    }

//	public static Builder builder() {
//		return new Builder();
//	}

//	@Getter
//	@NoArgsConstructor
//	@AllArgsConstructor
//	@lombok.Builder
//	public static final class Builder {
//		private OrderItemId orderItemId;
//		private OrderId orderId;
//		private Product product;
//		private Integer quantity;
//		private Money price;
//		private Money subTotal;
//
//	}

}
