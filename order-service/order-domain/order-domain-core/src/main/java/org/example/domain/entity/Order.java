package org.example.domain.entity;

import java.util.List;
import java.util.UUID;

import org.example.domain.exception.OrderDomainException;
import org.example.domain.valueObject.OrderItemId;
import org.example.domain.valueObject.StreetAddress;
import org.example.domain.valueObject.TrackingId;
import org.example.domain.valueobject.CustomerId;
import org.example.domain.valueobject.Money;
import org.example.domain.valueobject.OrderId;
import org.example.domain.valueobject.OrderStatus;
import org.example.domain.valueobject.RestaurantId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class Order extends AggregateRoot<OrderId> {

	private final OrderId orderId;
	private final CustomerId customerId;
	private final RestaurantId restaurantId;
	private StreetAddress streetAddress;
	// tổng giá trị đơn hàng
	private Money price;
	private TrackingId trackingId;
	private OrderStatus orderStatus;
	private List<OrderItem> items;
	private List<String> failureMessages;

	// dùng để khởi tạo order mới trước khi lưu vào DB và gửi qua các service khác
	// để xử lý
	// (tạo id, trạng thái ban đầu, khởi tạo các order item)
	public void initializeOrder() {
		setId(new OrderId(UUID.randomUUID()));
		trackingId = new TrackingId(UUID.randomUUID());
		orderStatus = OrderStatus.PENDING;
		initializeOrderItems();
	}

	public void validateOrder() {
		validateInitialOrder();
		validateTotalPrice();
		validateItemsPrice();
	}

	private void validateInitialOrder() {
		// kiểm tra status đơn hàng và id đơn hàng khác null
		if (this.orderStatus != null || this.getId() != null) {
			throw new OrderDomainException("Order is not in correct state for initialization");
		}
	}

	private void validateTotalPrice() {
		// kiểm tra tổng giá trị đơn hàng
		if (this.price == null || !this.price.isGreateThanZero()) {
			throw new IllegalStateException("Order total price is not valid");
		}
	}

	// validate Price = total = item.subtotal +
	private void validateItemsPrice() {

		Money total = items.stream().map(orderItem -> {
			// validate giá tiền từng item
			validateItemPrice(orderItem);
			return orderItem.getSubTotal();
			// lấy tổng giá tiền các item trong đơn hàng
		}).reduce(Money.ZERO, Money::add);

		// so sánh tổng giá tiền các item với tổng giá tiền đơn hàng
		if (!price.equals(total)) {
			throw new OrderDomainException("Total price: " + price + " is not equal to sum of item prices: " + total);
		}

	}

	private void validateItemPrice(OrderItem orderItem) {
		if (!orderItem.isPriceValid()) {
			throw new OrderDomainException("Total price: " + orderItem.getPrice().getAmount() + " is not valid for product "
					+ orderItem.getProduct().getId().getValue());
		}

	}

	// khởi tạo các order item (tạo id, gán orderId) cho từng item trong order trước
	// khi lưu vào DB
	public void initializeOrderItems() {
		long itemId = 1;
		for (OrderItem item : items) {
			item.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
		}
	}

	public Order(Builder builder) {
		super(builder.orderId);
		this.orderId = builder.orderId;
		this.customerId = builder.customerId;
		this.restaurantId = builder.restaurantId;
		this.streetAddress = builder.streetAddress;
		this.price = builder.price;
		this.trackingId = builder.trackingId;
		this.orderStatus = builder.orderStatus;
		this.items = builder.items;
		this.failureMessages = builder.failureMessages;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static final class Builder {
		private OrderId orderId;
		private CustomerId customerId;
		private RestaurantId restaurantId;
		private StreetAddress streetAddress;
		private Money price;
		private TrackingId trackingId;
		private OrderStatus orderStatus;
		private List<OrderItem> items;
		private List<String> failureMessages;
	}

	public boolean validateOrder(Order order) {
		if (order.getStreetAddress() == null) {
			return false;
		}
		if (order.getPrice() == null
				|| !order.getPrice().isGreateThanZero()) {
			return false;
		}
		if (order.getItems() == null || order.getItems().isEmpty()) {
			return false;
		}
		return true;
	}

	// 1 đơn hàng sẽ có các trạng thái :
	/**
	 * Khi vừa đặt hàng thì sẽ là pending, tại pending thì cho phép thanh toán hoặc
	 * hủy đơn hàng
	 * Sau khi thanh toán thì status là paid. Có thể cho phép hủy đơn hàng hoặc là
	 * approve
	 */

	// phương thức thanh toán:
	public void pay() {
		if (this.orderStatus != null && this.orderStatus == OrderStatus.PENDING) {
			this.orderStatus = OrderStatus.PAID;
		}
	}

	// phương thức chấp nhận
	public void approve() {
		if (this.orderStatus != null && this.orderStatus == OrderStatus.PAID) {
			this.orderStatus = OrderStatus.APPROVED;
		}
	}

	public void initCancel(List<String> failureMessages) {
		if (this.orderStatus != null && (this.orderStatus == OrderStatus.PAID
				|| this.orderStatus == OrderStatus.PENDING)) {
			this.orderStatus = OrderStatus.CANCELLING;
			this.failureMessages = failureMessages;
		}
	}

	public void cancel(List<String> failureMessages) {
		if (this.orderStatus != null && (this.orderStatus == OrderStatus.CANCELLING
				|| this.orderStatus == OrderStatus.PENDING
				|| this.orderStatus == OrderStatus.PAID)) {
			this.orderStatus = OrderStatus.CANCELLED;
			// this.failureMessages = failureMessages;
			updateFailureMessages(failureMessages);
		}
	}

	private void updateFailureMessages(List<String> failureMessages) {
		if (this.failureMessages != null && failureMessages != null && failureMessages.size() > 0) {
			this.failureMessages.addAll(failureMessages);
		}
		
		if (this.failureMessages == null) {
			this.failureMessages = failureMessages;
		}

	}

}
