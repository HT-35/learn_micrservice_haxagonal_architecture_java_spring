package org.example.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.example.domain.entity.Order;
import org.example.domain.entity.Product;
import org.example.domain.entity.Restaurant;
import org.example.domain.event.OrderCancelledEvent;
import org.example.domain.event.OrderCreatedEvent;
import org.example.domain.event.OrderPaidEvent;
import org.example.domain.exception.OrderDomainException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

	private static final String UTC = "UTC";

	@Override
	public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {

		validateRestaurant(restaurant);
		setOrderProductInformation(order, restaurant);
		order.validateOrder();
		order.initializeOrder();
		log.info("Order with id : {} is initiated",order.getId().getValue());
		return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
	}




	// trả tiền cho đơn hàng (đơn hàng chuyển trạng thái từ PENDING sang PAID)
	@Override
	public OrderPaidEvent payOrder(Order order) {
		order.pay();
		log.info("Order with id : {} is paid",order.getId().getValue());
		
		return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
	}

	// duyệt đơn hàng (đơn hàng chuyển trạng thái từ PAID sang APPROVED)
	// chỉ có thể duyệt đơn hàng khi đơn hàng đã được trả tiền
	// nếu đơn hàng chưa được trả tiền thì sẽ ném ra ngoại lệ
	// (không thể duyệt đơn hàng khi chưa trả tiền)
	// nếu đơn hàng đã được duyệt rồi thì sẽ ném ra ngoại lệ
	// (không thể duyệt đơn hàng đã được duyệt rồi)
	// nếu đơn hàng đã bị hủy thì sẽ ném ra ngoại lệ
	// (không thể duyệt đơn hàng đã bị hủy)
	// nếu đơn hàng đang ở trạng thái khác PAID thì sẽ ném ra ngoại lệ
	@Override
	public void approveOrder(Order order) {
		order.approve();
		log.info("Order with id : {} is approved",order.getId().getValue());
		
	}

	@Override
	public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
		order.initCancel(failureMessages);
		log.info("Order payment is cancelling for order id :  {}", order.getId());
		return new OrderCancelledEvent(order,ZonedDateTime.now(ZoneId.of(UTC)));
	}

	@Override
	public void cancelOrder(Order order, List<String> failureMessages) {
		order.cancel(failureMessages);
		log.info("Order is cancelled for order id :  {}", order.getId());
	}
	

	// kiểm tra nhà hàng có hoạt động không
	private void validateRestaurant(Restaurant restaurant) {
		if (!restaurant.isActive()) {
		throw new OrderDomainException("Restaurant with id " + restaurant.getId().getValue() + " is not active");
		}
	}

	

	// thiết lập thông tin sản phẩm trong đơn hàng từ thông tin sản phẩm của nhà hàng
	// (giá, tên sản phẩm)
	// để tránh việc khách hàng thay đổi thông tin sản phẩm khi tạo đơn hàng
	// (ví dụ: thay đổi giá sản phẩm)
	// chỉ lấy id sản phẩm từ phía khách hàng
	// và lấy các thông tin còn lại từ phía nhà hàng
	// đảm bảo tính nhất quán của dữ liệu
	// nếu không tìm thấy sản phẩm trong nhà hàng thì sẽ không thể tạo đơn hàng
	// và ném ra ngoại lệ
	// vì sản phẩm không hợp lệ
	private void setOrderProductInformation(Order order, Restaurant restaurant) {
		order.getItems().stream().forEach(orderItem -> restaurant.getProducts().stream().forEach(restaurantProduct -> {
			
			Product currentProduct = orderItem.getProduct();

			if (currentProduct.equals(restaurantProduct)) {
				currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
			}

		}));

	}


}
